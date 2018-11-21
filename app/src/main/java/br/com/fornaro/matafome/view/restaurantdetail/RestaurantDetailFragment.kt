package br.com.fornaro.matafome.view.restaurantdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import br.com.fornaro.matafome.R
import br.com.fornaro.matafome.common.Resource
import br.com.fornaro.matafome.view.MainApplication
import br.com.fornaro.matafome.viewmodel.RestaurantDetailViewModel
import javax.inject.Inject

class RestaurantDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[RestaurantDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MainApplication).appComponent.inject(this)

        val safeArgs = RestaurantDetailFragmentArgs.fromBundle(arguments)
        val restaurantId = safeArgs.restaurantId
        setupViewModel(restaurantId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    private fun setupViewModel(restaurantId: String) {
        viewModel.getRestaurantDetail(restaurantId).observe(this, Observer { resources ->
            when (resources.status) {
                Resource.Status.SUCCESS -> {
                }
                Resource.Status.ERROR -> {
                }
                Resource.Status.LOADING -> {
                }
            }
        })
    }
}
