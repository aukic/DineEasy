package ukic.ante.dineeasy.ui.mainmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ukic.ante.dineeasy.databinding.FragmentReservationsBinding

class ReservationFragment:Fragment() {
    private lateinit var binding: FragmentReservationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationsBinding.inflate(layoutInflater)

        return binding.root
    }
}