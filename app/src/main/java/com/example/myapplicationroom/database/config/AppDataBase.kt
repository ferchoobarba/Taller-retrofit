package com.example.myapplicationroom.database.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplicationroom.database.dao.UserDao
import com.example.myapplicationroom.database.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
}