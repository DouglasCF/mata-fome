package br.com.fornaro.matafome.viewmodel

import androidx.lifecycle.ViewModel
import br.com.fornaro.matafome.database.entities.CartItem
import br.com.fornaro.matafome.model.Restaurant
import br.com.fornaro.matafome.model.RestaurantDetail
import br.com.fornaro.matafome.repository.CartRepository
import javax.inject.Inject

class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel() {

    fun getCartItems() = cartRepository.getAll()

    fun insertCartItem(restaurant: Restaurant, restaurantDetail: RestaurantDetail, quantity: Int) {
        val cartItem = CartItem(
            0L, restaurant.name, restaurant.deliveryFee, restaurant.imageUrl,
            restaurantDetail.name, restaurantDetail.price, restaurantDetail.imageUrl, quantity
        )
        cartRepository.insert(cartItem)
    }
}