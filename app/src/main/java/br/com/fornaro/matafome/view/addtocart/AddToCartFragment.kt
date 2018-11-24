package br.com.fornaro.matafome.view.addtocart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import br.com.fornaro.matafome.databinding.FragmentAddToCartBinding
import br.com.fornaro.matafome.model.RestaurantDetail
import kotlinx.android.synthetic.main.fragment_add_to_cart.*

class AddToCartFragment : Fragment() {

    private lateinit var binding: FragmentAddToCartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddToCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs = AddToCartFragmentArgs.fromBundle(arguments)
        val restaurantDetail = safeArgs.restaurantDetail

        assignToBinding(restaurantDetail!!)
        setupAddToCartButton()
    }

    private fun assignToBinding(restaurantDetail: RestaurantDetail) {
        binding.restaurantDetail = restaurantDetail
    }

    private fun setupAddToCartButton() {
        addToCartButton.setOnClickListener {
            // Add to cart

            NavHostFragment.findNavController(this).navigateUp()
        }
    }
}
