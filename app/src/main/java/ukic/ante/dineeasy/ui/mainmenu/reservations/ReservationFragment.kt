package ukic.ante.dineeasy.ui.mainmenu.reservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ukic.ante.dineeasy.databinding.FragmentReservationTabBinding

class ReservationFragment:Fragment() {
    private lateinit var binding: FragmentReservationTabBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private var tabTitle = arrayOf("Active reservations","Past reservations")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationTabBinding.inflate(layoutInflater)
        tabLayout = binding.tlReservations
        viewPager = binding.vpReservations

        viewPager.adapter = TabLayoutAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout,viewPager){
            tab, position -> tab.text = tabTitle[position]
        }.attach()

        return binding.root
    }
}


