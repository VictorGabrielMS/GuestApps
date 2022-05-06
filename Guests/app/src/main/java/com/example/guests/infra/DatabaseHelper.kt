package com.example.guests.infra

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.guests.core.Constants

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {
    companion object {
        // database version
        private const val VERSION = 1
        //database name
        private const val NAME = Constants.DATABASE.NAME

        // script to create table on database
        private const val CREATE_TABLE_GUEST =
            ("create table " + Constants.DATABASE.TABLES.GUEST.NAME + " ("
                    + Constants.DATABASE.TABLES.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
                    + Constants.DATABASE.TABLES.GUEST.COLUMNS.NAME + " text, "
                    + Constants.DATABASE.TABLES.GUEST.COLUMNS.PRESENCE + " integer);")
    }

    /**
     * method that`s run once when the database is initialized for the first time
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GUEST)
    }

    /**
     * method that`s run when the database version is changed
     * in this case the application know the database change and run the update script
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

}