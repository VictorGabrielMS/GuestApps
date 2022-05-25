package com.example.guests.modules.guest.repositories

import android.content.Context
import com.example.guests.infra.GuestDatabase
import com.example.guests.modules.guest.models.GuestModel

class GuestRepository(context: Context) {
    // get database access
    private val mDatabase = GuestDatabase.getDatabase(context).guestDAO()

    fun getAll(): List<GuestModel> {
        return mDatabase.getAll()
    }

    fun getById(id: Int): GuestModel {
        return mDatabase.getById(id)
    }

    fun getByType(type: Int): List<GuestModel> {
        return mDatabase.getByType(type)
    }

    fun save(guest: GuestModel): Boolean {
        return mDatabase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return mDatabase.update(guest) > 0
    }

    fun delete(guest: GuestModel) {
        mDatabase.delete(guest)
    }
}