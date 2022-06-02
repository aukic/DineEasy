package ukic.ante.dineeasy.ui.mainmenu

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ukic.ante.dineeasy.R
import ukic.ante.dineeasy.databinding.ActivityMainBinding
import ukic.ante.dineeasy.ui.authorization.AuthorizationActivity
import ukic.ante.dineeasy.ui.authorization.SignInFragment
import ukic.ante.dineeasy.ui.authorization.SignInFragmentDirections
import ukic.ante.dineeasy.ui.mainmenu.reservations.ReservationFragment

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private var appBarConfiguration: AppBarConfiguration? = null
    private lateinit var drawerLayout: DrawerLayout
    private var toolbar: Toolbar? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = findViewById(R.id.toolbar)
        toolbar?.title = "DineEasy"
        setSupportActionBar(toolbar)

        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainMenuFragment,
                R.id.reservationFragment,
                R.id.aboutUsFragment,
                R.id.profileFragment,
                R.id.nav_logout
            ),drawerLayout
        )


        setupActionBarWithNavController(navController!!,appBarConfiguration!!)
        navView.setupWithNavController(navController!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration!!) || super.onSupportNavigateUp()
    }


    fun onClickLogout(item: MenuItem) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this,AuthorizationActivity::class.java)
        startActivity(intent)
        finish()
    }
}