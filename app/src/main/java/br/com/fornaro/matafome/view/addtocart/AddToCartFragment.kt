package br.com.fornaro.matafome.view.addtocart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.fornaro.matafome.databinding.FragmentAddToCartBinding
import br.com.fornaro.matafome.model.RestaurantDetail

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
    }

    private fun assignToBinding(restaurantDetail: RestaurantDetail) {
        binding.restaurantDetail = restaurantDetail
    }
}
