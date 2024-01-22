package com.fz.zakustore.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fz.zakustore.database.entities.teamEntity
import com.fz.zakustore.database.entities.zakuEntity
import com.fz.zakustore.zaku


@Dao
interface zakuDao {

    @Query("SELECT * FROM MobileSuit_Table")
    suspend fun getAllZakus():List<zakuEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(zakus:List<zakuEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(zaku: zakuEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertZakuAndTeam(zaku: zakuEntity, team: teamEntity)

    @Update
    suspend fun update(zaku: zakuEntity)

    @Delete
    fun delete(zaku: zakuEntity)

    @Delete
    suspend fun deleteList(zakus: List<zakuEntity>)

    @Query("DELETE FROM MobileSuit_Table")
    suspend fun deleteAllZakus()

    @Query("SELECT * FROM MobileSuit_Table WHERE id = :id")
    suspend fun getId(id: Int): zakuEntity
}

@Dao
interface TeamDao {

    @Query("SELECT * FROM Team_Table WHERE teamid = :id")
    suspend fun getTeamById(id: Int): teamEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: teamEntity)

    @Update
    suspend fun updateTeam(team: teamEntity)

    @Delete
    suspend fun deleteTeam(team: teamEntity)

}