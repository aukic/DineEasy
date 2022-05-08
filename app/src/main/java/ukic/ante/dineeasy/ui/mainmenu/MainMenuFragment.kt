package ukic.ante.dineeasy.ui.mainmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ukic.ante.dineeasy.databinding.FragmentMenuBinding


class MainMenuFragment: Fragment() {
    private lateinit var binding:FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)

        return binding.root
    }
}