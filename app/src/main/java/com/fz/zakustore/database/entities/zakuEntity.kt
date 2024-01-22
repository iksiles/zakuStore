package com.fz.zakustore.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MobileSuit_Table")
data class zakuEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "nick")val nick: String,
    @ColumnInfo(name = "photo")val photo: String
)