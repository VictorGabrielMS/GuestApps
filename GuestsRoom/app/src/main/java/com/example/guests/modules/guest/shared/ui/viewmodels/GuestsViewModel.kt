package com.example.guests.modules.guest.shared.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guests.modules.guest.models.GuestModel
import com.example.guests.modules.guest.repositories.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val guestRepository: GuestRepository = GuestRepository(application.applicationContext)

    private val _guestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = _guestList

    fun load(filter: Int) {
        when (filter) {
            0 -> _guestList.value = guestRepository.getByType(filter)
            1 -> _guestList.value = guestRepository.getByType(filter)
            2 -> _guestList.value = guestRepository.getAll()
        }
    }

    fun delete(id: Int) {
        guestRepository.delete(
            guestRepository.getById(id)
        )
    }
}