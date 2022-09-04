package ukic.ante.dineeasy.ui.mainmenu.reservations

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import ukic.ante.dineeasy.databinding.FragmentReservationsActiveBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ukic.ante.dineeasy.R
import ukic.ante.dineeasy.model.Reservation
import ukic.ante.dineeasy.presentation.ReservationListViewModel

class ActiveReservationFragment:Fragment() , OnReservationEventListener{
    private lateinit var binding: FragmentReservationsActiveBinding
    private lateinit var adapter: ReservationAdapter
    private val viewModel: ReservationListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationsActiveBinding.inflate(layoutInflater)

        setUpRecyclerView()
        fillRecycler()

        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.rvActiveReservations.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = ReservationAdapter()
        adapter.onReservationEventListener = this
        binding.rvActiveReservations.adapter = adapter
    }

    override fun onReservationSelected(reservation: Reservation?) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Are you sure you want to cancel your reservation?")
        builder.setPositiveButton("Yes"){dialog,which ->
            reservation?.let { it ->
                viewModel.deleteReservation(it)
                Toast.makeText(requireContext(), "Reservation cancelled", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("No"){dialog,which ->

        }
        builder.show()
    }

    override fun fillRecycler() {
        viewModel.activeReservations.observe(viewLifecycleOwner){
            if(it != null && it.isNotEmpty()){
                adapter.setReservations(it)
            }
        }
    }
}