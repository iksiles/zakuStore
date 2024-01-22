package com.fz.zakustore.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fz.zakustore.database.dao.zakuDao
import com.fz.zakustore.database.entities.zakuEntity

@Database(entities = [zakuEntity::class], version = 1, exportSchema = false)

abstract class zakuDatabase: RoomDatabase() {

    abstract fun zakuDao(): zakuDao

}