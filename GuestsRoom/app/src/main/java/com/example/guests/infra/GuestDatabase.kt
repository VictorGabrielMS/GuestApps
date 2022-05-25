package com.example.guests.infra

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.guests.core.Constants
import com.example.guests.modules.guest.dao.GuestDAO
import com.example.guests.modules.guest.models.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDatabase : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE: GuestDatabase
        fun getDatabase(context: Context): GuestDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDatabase::class) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context,
                            GuestDatabase::class.java,
                            Constants.DATABASE.NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun guestDAO(): GuestDAO
}