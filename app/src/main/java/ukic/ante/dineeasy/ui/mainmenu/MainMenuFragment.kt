package ukic.ante.dineeasy.ui.mainmenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ukic.ante.dineeasy.databinding.FragmentMenuBinding
import ukic.ante.dineeasy.ui.authorization.AuthorizationActivity
import ukic.ante.dineeasy.ui.restaurantlist.RestaurantListActivity


class MainMenuFragment: Fragment() {
    private lateinit var binding:FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)

        binding.btnBookTable.setOnClickListener { showRestaurantList() }
        return binding.root
    }

    private fun showRestaurantList() {
        val intent = Intent(context,RestaurantListActivity::class.java)
        startActivity(intent)
    }
}