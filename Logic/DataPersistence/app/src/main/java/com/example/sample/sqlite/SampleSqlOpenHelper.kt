package com.example.sample.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_VERSION = 1
val DATABASE_NAME = "Sample.db"
val SAMPLE_TABLE_NAME = "SampleTable"

class SampleSqlOpenHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL(
            "create table if not exists " + SAMPLE_TABLE_NAME +
                    " (id integer primary key autoincrement, name text, age integer)");
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            database?.execSQL("alter table " + SAMPLE_TABLE_NAME +
                    " add column deleteFlag integer default 0")
        }
    }
}