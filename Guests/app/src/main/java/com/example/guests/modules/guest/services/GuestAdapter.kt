package com.example.guests.modules.guest.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.R
import com.example.guests.modules.guest.models.GuestModel

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var mGuestList: List<GuestModel> = arrayListOf()
    private lateinit var mListener: GuestListener

    /**
     * this method is used to build the layout of the all rows of the recycle view list
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        return GuestViewHolder(
            listener = mListener,
            itemView = LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.guest_list_row,
                    parent,
                    false
                )
        )
    }

    /**
     * this method is used to bind action/values/attributes on each list item
     * is called on each item of the list
     */
    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(mGuestList[position])
    }

    /**
     * this method is use to get the list size
     */
    override fun getItemCount(): Int {
        return mGuestList.count()
    }

    /**
     * this method is use attach events on each list item
     */
    fun attachListener(listener: GuestListener) {
        mListener = listener
    }

    /**
     * this method is use to update the guest list on the class scope
     */
    fun onUpdateGuests(list: List<GuestModel>) {
        mGuestList = list
        notifyDataSetChanged()
    }
}