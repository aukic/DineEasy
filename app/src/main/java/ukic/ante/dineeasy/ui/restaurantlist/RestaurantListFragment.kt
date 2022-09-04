package ukic.ante.dineeasy.ui.restaurantlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import ukic.ante.dineeasy.R
import ukic.ante.dineeasy.databinding.FragmentListOfRestaurantsBinding
import ukic.ante.dineeasy.model.Reservation
import ukic.ante.dineeasy.ui.authorization.SignInFragmentDirections
import java.sql.Date
import java.sql.Time


class RestaurantListFragment:Fragment() {
    private lateinit var binding: FragmentListOfRestaurantsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListOfRestaurantsBinding.inflate(layoutInflater)

        binding.haustor.setOnClickListener { showPopUp(getString(R.string.haustor)) }
        binding.lari.setOnClickListener { showPopUp(getString(R.string.lari)) }
        binding.maliBar.setOnClickListener { showPopUp(getString(R.string.maliBar)) }
        binding.plac.setOnClickListener { showPopUp(getString(R.string.plac)) }
        binding.salsa.setOnClickListener { showPopUp(getString(R.string.salsa)) }
        binding.rouge.setOnClickListener { showPopUp(getString(R.string.rouge)) }
        return binding.root
    }

    private fun showPopUp(restaurantName: String) {
        var reservation = Reservation(restaurantName, Time(0,0,0),"", Date(0,0,0))
        val action = RestaurantListFragmentDirections.actionRestaurantListFragmentToArrivalSelectionFragment(reservation)
        findNavController().navigate(action)
    }
}