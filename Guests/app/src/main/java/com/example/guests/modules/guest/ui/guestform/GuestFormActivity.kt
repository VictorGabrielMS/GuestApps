package com.example.guests.modules.guest.ui.guestform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.guests.R
import com.example.guests.core.Constants
import com.example.guests.databinding.ActivityGuestFormBinding


class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var guestFormViewModel: GuestFormViewModel
    private var guestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewModelProvider(this)[GuestFormViewModel::class.java].also {
            guestFormViewModel = it
        }

        listeners()
        observers()
        load()

        binding.radioPresent.isChecked = true
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_save -> {
                guestFormViewModel.save(
                    id = guestId,
                    name = binding.editName.text.toString(),
                    presence = binding.radioPresent.isChecked
                )
            }
        }
    }

    private fun load() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(Constants.GUESTDEFS.GUESTID)
            guestFormViewModel.load(guestId)
        }
    }

    private fun listeners() {
        binding.buttonSave.setOnClickListener(this)
    }

    private fun observers() {
        guestFormViewModel.presence.observe(
            this
        ) {
            if (it) {
                Toast
                    .makeText(
                        applicationContext,
                        R.string.action_success,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            } else {
                Toast
                    .makeText(
                        applicationContext,
                        R.string.action_error,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
            finish()
        }

        guestFormViewModel.guest.observe(
            this
        ) {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        }
    }

}