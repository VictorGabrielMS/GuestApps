package com.example.guests.modules.guest.dao

import androidx.room.*
import com.example.guests.core.Constants
import com.example.guests.modules.guest.models.GuestModel

@Dao
interface GuestDAO {
    @Insert
    fun save(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM ${Constants.DATABASE.TABLES.GUEST.NAME}")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM ${Constants.DATABASE.TABLES.GUEST.NAME} WHERE id = :id")
    fun getById(id: Int): GuestModel

    @Query("SELECT * FROM ${Constants.DATABASE.TABLES.GUEST.NAME} WHERE presence = :presence")
    fun getByType(presence: Int): List<GuestModel>
}