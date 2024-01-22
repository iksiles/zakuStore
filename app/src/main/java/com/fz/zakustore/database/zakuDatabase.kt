package com.fz.zakustore.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fz.zakustore.database.dao.TeamDao
import com.fz.zakustore.database.dao.zakuDao
import com.fz.zakustore.database.entities.teamEntity
import com.fz.zakustore.database.entities.zakuEntity

@Database(entities = [zakuEntity::class, teamEntity::class], version = 4, exportSchema = false)

abstract class zakuDatabase: RoomDatabase() {

    abstract fun zakuDao(): zakuDao
    abstract fun teamDao(): TeamDao
}