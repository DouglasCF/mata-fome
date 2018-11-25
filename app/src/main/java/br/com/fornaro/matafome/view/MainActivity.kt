package br.com.fornaro.matafome.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.fornaro.matafome.R
import br.com.fornaro.matafome.databinding.ActivityMainBinding
import br.com.fornaro.matafome.view.cart.CartActivity
import br.com.fornaro.matafome.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory)[CartViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        (application as MainApplication).appComponent.inject(this)

        setSupportActionBar(toolbar)

        val navController = (hostFragment as NavHostFragment).navController
        setupActionBar(navController)

        setupViewModel()
        setupCartLayout()
    }

    override fun onSupportNavigateUp() =
            NavigationUI.navigateUp(findNavController(R.id.hostFragment), null)

    private fun setupActionBar(navController: NavController) =
            setupActionBarWithNavController(navController)

    private fun setupViewModel() {
        viewModel.getCartView().observe(this, Observer {
            binding.cartView = it
        })
    }

    private fun setupCartLayout() {
        cartLayout.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java)) }
    }
}
