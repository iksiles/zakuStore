package com.fz.zakustore.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "MobileSuit_Table", foreignKeys = [ForeignKey(entity = teamEntity::class, parentColumns = ["teamid"], childColumns = ["teamid"])])
data class zakuEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "nick") val nick: String,
    @ColumnInfo(name = "teamid") val teamid: Int, // Cambiado a teamid para que coincida con el nombre de la columna en teamEntity
    @ColumnInfo(name = "photo") val photo: String
)

@Entity(tableName = "Team_Table", foreignKeys = [ForeignKey(entity = teamEntity::class, parentColumns = ["teamid"], childColumns = ["teamid"], onDelete = ForeignKey.CASCADE)])
data class teamEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "teamid") val teamid: Int
)