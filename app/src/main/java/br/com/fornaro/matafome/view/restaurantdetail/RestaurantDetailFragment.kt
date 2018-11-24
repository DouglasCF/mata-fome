package br.com.fornaro.matafome.view.restaurantdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fornaro.matafome.common.Resource
import br.com.fornaro.matafome.databinding.FragmentRestaurantDetailBinding
import br.com.fornaro.matafome.model.Restaurant
import br.com.fornaro.matafome.view.MainActivity
import br.com.fornaro.matafome.view.MainApplication
import br.com.fornaro.matafome.viewmodel.RestaurantDetailViewModel
import kotlinx.android.synthetic.main.layout_content_restaurants.*
import javax.inject.Inject

class RestaurantDetailFragment : Fragment() {

    private lateinit var binding: FragmentRestaurantDetailBinding
    private lateinit var restaurant: Restaurant

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[RestaurantDetailViewModel::class.java]
    }
    private val viewAdapter by lazy {
        RestaurantDetailAdapter {
            val action = RestaurantDetailFragmentDirections.nextAction()
            action.setRestaurant(restaurant)
            action.setRestaurantDetail(it)
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MainApplication).appComponent.inject(this)

        val safeArgs = RestaurantDetailFragmentArgs.fromBundle(arguments)
        restaurant = safeArgs.restaurant ?: throw Exception("restaurant cant be null")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel(restaurant.id)
        assignToBinding(restaurant)
        changeActionBarTitle("${restaurant.name} - ${restaurant.type}")
        setupRecyclerView()
    }

    private fun assignToBinding(restaurant: Restaurant) {
        binding.restaurant = restaurant
    }

    private fun changeActionBarTitle(title: String) {
        (activity as MainActivity).supportActionBar?.title = title
    }

    private fun setupViewModel(restaurantId: String) {
        viewModel.getRestaurantDetail(restaurantId).observe(this, Observer { resources ->
            binding.resources = resources
            when (resources.status) {
                Resource.Status.SUCCESS -> {
                    resources.data?.let { viewAdapter.setData(it) }
                }
                Resource.Status.ERROR -> {
                }
                Resource.Status.LOADING -> {
                }
            }
        })
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(activity)
            adapter = viewAdapter
        }
    }
}
