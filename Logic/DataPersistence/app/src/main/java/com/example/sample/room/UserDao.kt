package com.example.sample.room

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insert(user : User)

    @Update
    fun update(user : User)

    @Delete
    fun delete(user : User)

    @Query("select * from users")
    fun getAll(): List<User>

    @Query("select * from users where uid = :id")
    fun getUser(id: Int): User

    @Query("delete from users")
    fun deleteAll()
}