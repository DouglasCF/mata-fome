package br.com.fornaro.matafome.view.addtocart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import br.com.fornaro.matafome.databinding.FragmentAddToCartBinding
import br.com.fornaro.matafome.model.Restaurant
import br.com.fornaro.matafome.model.RestaurantDetail
import br.com.fornaro.matafome.view.MainApplication
import br.com.fornaro.matafome.viewmodel.AddToCartViewModel
import kotlinx.android.synthetic.main.fragment_add_to_cart.*
import javax.inject.Inject

class AddToCartFragment : Fragment() {

    private lateinit var binding: FragmentAddToCartBinding
    private lateinit var restaurant: Restaurant
    private lateinit var restaurantDetail: RestaurantDetail

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[AddToCartViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MainApplication).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddToCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs = AddToCartFragmentArgs.fromBundle(arguments)
        restaurant = safeArgs.restaurant ?: throw Exception("restaurant cant be null")
        restaurantDetail = safeArgs.restaurantDetail ?: throw Exception("restaurantDetail cant be null")

        assignToBinding()
        setupAddToCartButton()
        setupChangeQuantityButtons()
    }

    private fun assignToBinding() {
        binding.restaurantDetail = restaurantDetail
        binding.viewModel = viewModel
    }

    private fun setupAddToCartButton() {
        addToCartButton.setOnClickListener {
            viewModel.insertCartItem(restaurant, restaurantDetail)

            NavHostFragment.findNavController(this).navigateUp()
        }
    }

    private fun setupChangeQuantityButtons() {
        minusButton.setOnClickListener {
            viewModel.decreaseQuantity()
        }

        plusButton.setOnClickListener {
            viewModel.increaseQuantity()
        }
    }
}
