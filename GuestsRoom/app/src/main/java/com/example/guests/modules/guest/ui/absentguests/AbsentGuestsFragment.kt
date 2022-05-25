package com.example.guests.modules.guest.ui.absentguests

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guests.core.Constants
import com.example.guests.databinding.FragmentAbsentGuestsBinding
import com.example.guests.modules.guest.services.GuestAdapter
import com.example.guests.modules.guest.services.GuestListener
import com.example.guests.modules.guest.shared.ui.viewmodels.GuestsViewModel
import com.example.guests.modules.guest.ui.guestform.GuestFormActivity

class AbsentGuestsFragment : Fragment() {

    private var _binding: FragmentAbsentGuestsBinding? = null
    private lateinit var guestsViewModel: GuestsViewModel
    private val guestAdapter: GuestAdapter = GuestAdapter()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAbsentGuestsBinding.inflate(inflater, container, false)
        ViewModelProvider(this)[GuestsViewModel::class.java].also {
            guestsViewModel = it
        }

        binding.listAbsentGuests.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = guestAdapter
        }

        guestAdapter.attachListener(object : GuestListener {
            override fun onClick(id: Int) {
                startActivity(
                    Intent(context, GuestFormActivity::class.java).putExtras(
                        Bundle().apply {
                            this.putInt(Constants.GUESTDEFS.GUESTID, id)
                        }
                    )
                )
            }

            override fun onDelete(id: Int) {
                guestsViewModel.delete(id)
                guestsViewModel.load(Constants.GUESTDEFS.FILTER.ABSENTS)
            }
        })

        observers()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        guestsViewModel.load(Constants.GUESTDEFS.FILTER.ABSENTS)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observers() {
        guestsViewModel.guestList.observe(viewLifecycleOwner) {
            guestAdapter.onUpdateGuests(it)
        }
    }
}