package ukic.ante.dineeasy.ui.mainmenu.reservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ukic.ante.dineeasy.databinding.FragmentReservationsPastBinding

class PastReservationFragment:Fragment() {
    private lateinit var binding: FragmentReservationsPastBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationsPastBinding.inflate(layoutInflater)

        return binding.root
    }
}