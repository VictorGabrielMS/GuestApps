package com.example.guests.modules.guest.services

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.R
import com.example.guests.modules.guest.models.GuestModel

class GuestViewHolder(itemView: View, private val listener: GuestListener) :
    RecyclerView.ViewHolder(itemView) {
    fun bind(guest: GuestModel) {
        itemView
            .findViewById<TextView>(R.id.text_guest_list_row) // get the ui element
            .let {
                // set a value to it
                it.text = guest.name
                // set a click event to it
                it.setOnClickListener {
                    listener.onClick(guest.id)
                }
                // set a long click event to it
                it.setOnLongClickListener {
                    AlertDialog.Builder(itemView.context)
                        .setTitle(R.string.label_remove_guest)
                        .setMessage(R.string.desc_remove_guest)
                        .setPositiveButton(R.string.action_remove) { _, _ ->
                            listener.onDelete(guest.id)
                        }
                        .setNeutralButton(R.string.action_cancel, null)
                        .show()
                    true
                }
            }
    }
}