package com.example.guests.modules.guest.repositories

import android.content.ContentValues
import android.content.Context
import com.example.guests.core.Constants
import com.example.guests.infra.DatabaseHelper
import com.example.guests.modules.guest.models.GuestModel

class GuestRepository private constructor(context: Context) {
    // get a instance of database
    private var mDatabaseHelper: DatabaseHelper = DatabaseHelper(context)

    /**
     * Singleton
     */
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun getById(id: Int): GuestModel? {
        var guest: GuestModel? = null
        return try {
            mDatabaseHelper.readableDatabase.query(
                Constants.DATABASE.TABLES.GUEST.NAME,
                arrayOf(
                    Constants.DATABASE.TABLES.GUEST.COLUMNS.NAME,
                    Constants.DATABASE.TABLES.GUEST.COLUMNS.PRESENCE
                ),
                "${Constants.DATABASE.TABLES.GUEST.COLUMNS.ID} = ?",
                arrayOf(id.toString()),
                null, null, null
            ).let {
                if (it != null && it.count > 0) {
                    it.moveToFirst()

                    guest = GuestModel(
                        id = id,
                        name = it.getString(it.getColumnIndexOrThrow(Constants.DATABASE.TABLES.GUEST.COLUMNS.NAME)),
                        presence = (it.getInt(it.getColumnIndexOrThrow(Constants.DATABASE.TABLES.GUEST.COLUMNS.PRESENCE)) == 1)
                    )
                }

                it?.close()
                guest
            }
        } catch (e: Exception) {
            guest
        }
    }

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            mDatabaseHelper.readableDatabase.query(
                Constants.DATABASE.TABLES.GUEST.NAME,
                arrayOf(
                    Constants.DATABASE.TABLES.GUEST.COLUMNS.ID,
                    Constants.DATABASE.TABLES.GUEST.COLUMNS.NAME,
                    Constants.DATABASE.TABLES.GUEST.COLUMNS.PRESENCE
                ),
                null, null, null, null, null
            ).let {
                if (it != null && it.count > 0) {
                    while (it.moveToNext()) {
                        list.add(
                            GuestModel(
                                id = it.getInt(it.getColumnIndexOrThrow(Constants.DATABASE.TABLES.GUEST.COLUMNS.ID)),
                                name = it.getString(it.getColumnIndexOrThrow(Constants.DATABASE.TABLES.GUEST.COLUMNS.NAME)),
                                presence = (it.getInt(it.getColumnIndexOrThrow(Constants.DATABASE.TABLES.GUEST.COLUMNS.PRESENCE)) == 1)
                            )
                        )
                    }
                }

                it?.close()
                list
            }
        } catch (e: Exception) {
            list
        }
    }

    fun getByType(type: Int): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            mDatabaseHelper.readableDatabase.query(
                Constants.DATABASE.TABLES.GUEST.NAME,
                arrayOf(
                    Constants.DATABASE.TABLES.GUEST.COLUMNS.ID,
                    Constants.DATABASE.TABLES.GUEST.COLUMNS.NAME,
                    Constants.DATABASE.TABLES.GUEST.COLUMNS.PRESENCE
                ),
                "${Constants.DATABASE.TABLES.GUEST.COLUMNS.PRESENCE} = ?",
                arrayOf(type.toString()),
                null, null, null
            ).let {
                if (it != null && it.count > 0) {
                    while (it.moveToNext()) {
                        list.add(
                            GuestModel(
                                id = it.getInt(it.getColumnIndexOrThrow(Constants.DATABASE.TABLES.GUEST.COLUMNS.ID)),
                                name = it.getString(it.getColumnIndexOrThrow(Constants.DATABASE.TABLES.GUEST.COLUMNS.NAME)),
                                presence = (type == 1)
                            )
                        )
                    }
                }

                it?.close()
                list
            }
        } catch (e: Exception) {
            list
        }
    }

    fun save(guest: GuestModel): Boolean {
        return try {
            mDatabaseHelper.writableDatabase.insert(
                Constants.DATABASE.TABLES.GUEST.NAME,
                null,
                ContentValues().apply {
                    this.put(Constants.DATABASE.TABLES.GUEST.COLUMNS.NAME, guest.name)
                    this.put(Constants.DATABASE.TABLES.GUEST.COLUMNS.PRESENCE, guest.presence)
                }
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            mDatabaseHelper.writableDatabase.update(
                Constants.DATABASE.TABLES.GUEST.NAME,
                ContentValues().apply {
                    this.put(Constants.DATABASE.TABLES.GUEST.COLUMNS.NAME, guest.name)
                    this.put(Constants.DATABASE.TABLES.GUEST.COLUMNS.PRESENCE, guest.presence)
                },
                "${Constants.DATABASE.TABLES.GUEST.COLUMNS.ID} = ?",
                arrayOf(guest.id.toString())
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            mDatabaseHelper.writableDatabase.delete(
                Constants.DATABASE.TABLES.GUEST.NAME,
                "${Constants.DATABASE.TABLES.GUEST.COLUMNS.ID} = ?",
                arrayOf(id.toString())
            )
            true
        } catch (e: Exception) {
            false
        }
    }
}