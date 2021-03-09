package com.example.sample.room

import android.content.Context
import androidx.room.Room
import com.example.sample.log.Logging
import java.util.*

class UserManager {

    companion object {

        // DBは高コストのため、Singleton
        private var instance: UserManager? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: UserManager().also { instance = it }
        }
    }

    var database: AppDatabase? = null
    private fun getDB(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name")
            .allowMainThreadQueries() // 軽い処理しかないのでメインスレッドでの動作をさせる（本来は非推奨）
            .build()
    }

    fun insert(context: Context) {

        if (database == null) {
            database = getDB(context)
        }
        database?.let {
            val userDao = it.userDao()
            val newUser = User(0, Date().time.toString(), Date().time.toString(), 21)
            userDao.insert(newUser)
        }
    }

    fun selectAll(context: Context) {

        if (database == null) {
            database = getDB(context)
        }
        database?.let {
            val userDao = it.userDao()
            val users = userDao.getAll()
            for (user in users) {
                Logging.i("${user.toString()}")
            }
        }
    }

    fun deleteAll(context: Context) {

        if (database == null) {
            database = getDB(context)
        }
        database?.let {
            val userDao = it.userDao()
            userDao.deleteAll()
        }
    }
}