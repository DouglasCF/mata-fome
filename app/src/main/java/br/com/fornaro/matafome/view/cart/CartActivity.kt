package br.com.fornaro.matafome.view.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.fornaro.matafome.R
import kotlinx.android.synthetic.main.activity_main.*

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_up, R.anim.nav_default_pop_exit_anim)

        setContentView(R.layout.activity_cart)

        setSupportActionBar(toolbar)

        val navController = (hostFragment as NavHostFragment).navController
        setupActionBar(navController)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.slide_out_bottom)
    }

    override fun onSupportNavigateUp() =
            NavigationUI.navigateUp(findNavController(R.id.hostFragment), null)

    private fun setupActionBar(navController: NavController) =
            setupActionBarWithNavController(navController)
}
