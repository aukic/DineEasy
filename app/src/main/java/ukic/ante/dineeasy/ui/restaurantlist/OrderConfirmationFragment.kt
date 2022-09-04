package ukic.ante.dineeasy.ui.restaurantlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ukic.ante.dineeasy.data.ReservationRepositoryImpl
import ukic.ante.dineeasy.databinding.FragmentOrderConfirmationBinding
import ukic.ante.dineeasy.model.Reservation

class OrderConfirmationFragment:Fragment() {
    private lateinit var binding: FragmentOrderConfirmationBinding
    private var reservationRepository = ReservationRepositoryImpl()
    private lateinit var reservation: Reservation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderConfirmationBinding.inflate(layoutInflater)

        reservation = OrderConfirmationFragmentArgs.fromBundle(requireArguments()).reservation

        binding.etDate.text = reservation.dateOfArrival.toString()
        binding.etTime.text = reservation.timeOfArrival.toString()
        binding.etRestaurant.text = reservation.restaurantName
        binding.etStol.text = reservation.reservedTable

        binding.btnConfirm.setOnClickListener {
            reservationRepository.saveReservation(reservation)
            val action = OrderConfirmationFragmentDirections.actionOrderConfirmationFragmentToMainActivity2()
            findNavController().navigate(action)

        }

        return binding.root
    }
}