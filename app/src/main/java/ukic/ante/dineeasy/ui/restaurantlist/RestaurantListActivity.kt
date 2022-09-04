package ukic.ante.dineeasy.ui.restaurantlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ukic.ante.dineeasy.databinding.ActivityListOfRestaurantsBinding

class RestaurantListActivity:AppCompatActivity() {

    private lateinit var binding: ActivityListOfRestaurantsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListOfRestaurantsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}