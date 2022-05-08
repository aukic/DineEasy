package ukic.ante.dineeasy.ui.authorization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ukic.ante.dineeasy.R
import ukic.ante.dineeasy.databinding.ActivityAuthorizationBinding


class AuthorizationActivity: AppCompatActivity() {

    lateinit var binding: ActivityAuthorizationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}