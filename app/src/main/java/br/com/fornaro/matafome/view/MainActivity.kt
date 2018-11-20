package br.com.fornaro.matafome.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.fornaro.matafome.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val navController = (hostFragment as NavHostFragment).navController
        setupActionBar(navController)
    }

    override fun onSupportNavigateUp() =
            NavigationUI.navigateUp(findNavController(R.id.hostFragment), null)

    private fun setupActionBar(navController: NavController) =
            setupActionBarWithNavController(navController)
}
