package ukic.ante.dineeasy.ui.restaurantlist

import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import ukic.ante.dineeasy.R
import ukic.ante.dineeasy.databinding.DateTimeSelectionBinding
import ukic.ante.dineeasy.model.Reservation
import java.sql.Date
import java.sql.Time
import java.util.*

class ArrivalSelectionFragment:Fragment() {
    private lateinit var binding: DateTimeSelectionBinding
    private lateinit var reservation: Reservation
    private lateinit var datePicker: DatePicker
    private lateinit var spinnerHours: Spinner
    private lateinit var spinnerMinutes: Spinner
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DateTimeSelectionBinding.inflate(layoutInflater)
        datePicker = binding.datePicker
        setUpSpinners()

        reservation = ArrivalSelectionFragmentArgs.fromBundle(requireArguments()).reservation


        datePicker.minDate = System.currentTimeMillis()
        datePicker.maxDate = System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 14)

        binding.btnSubmit.setOnClickListener { showRestaurant() }

        return binding.root
    }

    private fun setUpSpinners() {
        spinnerHours = binding.spinnerHours
        spinnerMinutes = binding.spinnerMinutes

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinnerHours,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            spinnerHours.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinnerMinutes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            spinnerMinutes.adapter = adapter
        }
    }

    private fun showRestaurant(){
        var date = Date(datePicker.year-1900,datePicker.month,datePicker.dayOfMonth)
        var timeString = spinnerHours.selectedItem.toString() + ":" + spinnerMinutes.selectedItem.toString() + ":00"
        var time = Time.valueOf(timeString)

        reservation.dateOfArrival = date
        reservation.timeOfArrival = time

        if(reservation.restaurantName == getString(R.string.haustor)){
            val action = ArrivalSelectionFragmentDirections.actionArrivalSelectionFragmentToHaustorTableSelectionFragment(reservation)
            findNavController().navigate(action)
        }else if(reservation.restaurantName == getString(R.string.maliBar)){
            val action = ArrivalSelectionFragmentDirections.actionArrivalSelectionFragmentToMalibaTableSelectionFragment(reservation)
            findNavController().navigate(action)
        }
        else if(reservation.restaurantName == getString(R.string.lari)){
            val action = ArrivalSelectionFragmentDirections.actionArrivalSelectionFragmentToLariTableSelectionFragment(reservation)
            findNavController().navigate(action)
        }
        else if(reservation.restaurantName == getString(R.string.rouge)){
            val action = ArrivalSelectionFragmentDirections.actionArrivalSelectionFragmentToRougemarinTableSelectionFragment(reservation)
            findNavController().navigate(action)
        }
        else if(reservation.restaurantName == getString(R.string.plac)){
            val action = ArrivalSelectionFragmentDirections.actionArrivalSelectionFragmentToPlacTableSelectionFragment(reservation)
            findNavController().navigate(action)
        }
        else if(reservation.restaurantName == getString(R.string.salsa)){
            val action = ArrivalSelectionFragmentDirections.actionArrivalSelectionFragmentToSalsaTableSelectionFragment(reservation)
            findNavController().navigate(action)
        }
    }
}