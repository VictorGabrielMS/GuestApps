package com.example.guests.modules.guest.services

interface GuestListener {
    fun onClick(id: Int)
    fun onDelete(id: Int)
}