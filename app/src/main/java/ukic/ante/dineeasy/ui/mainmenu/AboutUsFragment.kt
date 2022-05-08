package ukic.ante.dineeasy.ui.mainmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ukic.ante.dineeasy.databinding.FragmentAboutUsBinding

class AboutUsFragment:Fragment() {
    lateinit var binding: FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutUsBinding.inflate(layoutInflater)
        return binding.root
    }
}