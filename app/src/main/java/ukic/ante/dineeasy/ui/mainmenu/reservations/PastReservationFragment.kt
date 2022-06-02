package ukic.ante.dineeasy.ui.mainmenu.reservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ukic.ante.dineeasy.databinding.FragmentReservationsPastBinding
import ukic.ante.dineeasy.presentation.ReservationListViewModel

class PastReservationFragment:Fragment() {
    private lateinit var binding: FragmentReservationsPastBinding
    private lateinit var adapter: ReservationAdapter
    private val viewModel: ReservationListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationsPastBinding.inflate(layoutInflater)

        setUpRecyclerView()
        viewModel.pastReservations.observe(viewLifecycleOwner){
            if (it != null && it.isNotEmpty()){
                adapter.setReservations(it)
            }
        }
        return binding.root
    }
    private fun setUpRecyclerView() {
        binding.rvPastReservations.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = ReservationAdapter()
        binding.rvPastReservations.adapter = adapter
    }
}