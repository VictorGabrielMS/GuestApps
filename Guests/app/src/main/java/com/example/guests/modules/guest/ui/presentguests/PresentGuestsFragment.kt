package com.example.guests.modules.guest.ui.presentguests

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guests.core.Constants
import com.example.guests.databinding.FragmentPresentGuestsBinding
import com.example.guests.modules.guest.services.GuestAdapter
import com.example.guests.modules.guest.services.GuestListener
import com.example.guests.modules.guest.shared.ui.viewmodels.GuestsViewModel
import com.example.guests.modules.guest.ui.guestform.GuestFormActivity

class PresentGuestsFragment : Fragment() {

    private var _binding: FragmentPresentGuestsBinding? = null
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
        _binding = FragmentPresentGuestsBinding.inflate(inflater, container, false)
        ViewModelProvider(this)[GuestsViewModel::class.java].also {
            guestsViewModel = it
        }

        binding.listPresentGuests.let {
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
                guestsViewModel.load(Constants.GUESTDEFS.FILTER.PRESENTS)

            }
        })

        observers()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        guestsViewModel.load(Constants.GUESTDEFS.FILTER.PRESENTS)
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