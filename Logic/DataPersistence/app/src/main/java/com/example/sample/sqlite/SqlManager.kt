package com.example.sample.sqlite

import android.content.ContentValues
import android.content.Context
import com.example.sample.log.Logging
import java.util.*


class SqlManager {

    private var nameList = arrayListOf<String>()
    private var ageList = arrayListOf<Int>()

    companion object {
        // Singleton
        private var instance: SqlManager? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: SqlManager().also { instance = it }
        }
    }

    fun insert(context: Context) {

        try {
            val dbHelper = SampleSqlOpenHelper(context)
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("name", Date().time.toString())
            values.put("age", 27)
            database.insertOrThrow(SAMPLE_TABLE_NAME, null, values)

        } catch(exception: Exception) {
            Logging.e(exception.toString())
        }
    }

    fun selectAll(context: Context) {

        try {
            nameList.clear();
            ageList.clear()

            val dbHelper = SampleSqlOpenHelper(context)
            val database = dbHelper.readableDatabase

            val sql = "select id, name, age from " + SAMPLE_TABLE_NAME + " order by id"

            val cursor = database.rawQuery(sql, null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    nameList.add(cursor.getString(1))
                    ageList.add(cursor.getInt(2))
                    cursor.moveToNext()
                }
            }
        } catch(exception: Exception) {
            Logging.e(exception.toString());
        }

        for (name in nameList) {
            Logging.i(name)
        }
    }

    fun deleteAll(context: Context) {

        try {
            val dbHelper = SampleSqlOpenHelper(context)
            val database = dbHelper.writableDatabase

            database.delete(SAMPLE_TABLE_NAME, null, null)

        } catch(exception: Exception) {
            Logging.e(exception.toString())
        }
    }
}