package com.example.guests.modules.guest.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.guests.core.Constants

// data class to refer a guest entity from database
@Entity(tableName = Constants.DATABASE.TABLES.GUEST.NAME)
class GuestModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.DATABASE.TABLES.GUEST.COLUMNS.ID)
    var id: Int = 0

    @ColumnInfo(name = Constants.DATABASE.TABLES.GUEST.COLUMNS.NAME)
    var name: String = ""

    @ColumnInfo(name = Constants.DATABASE.TABLES.GUEST.COLUMNS.PRESENCE)
    var presence: Boolean = true
}
