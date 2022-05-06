package com.example.guests.modules.guest.ui.guestform

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guests.modules.guest.models.GuestModel
import com.example.guests.modules.guest.repositories.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val guestRepository: GuestRepository = GuestRepository.getInstance(
        application.applicationContext
    )

    private val _presence = MutableLiveData<Boolean>()
    val presence: LiveData<Boolean> = _presence

    private val _guest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = _guest

    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel(id = id, name = name, presence = presence)
        if (id == 0) {
            _presence.value = guestRepository.save(guest)
        } else {
            _presence.value = guestRepository.update(guest)
        }
    }

    fun load(id: Int) {
        _guest.value = guestRepository.getById(id)
    }
}