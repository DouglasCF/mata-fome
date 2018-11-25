package br.com.fornaro.matafome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.fornaro.matafome.database.entities.CartItem
import br.com.fornaro.matafome.model.CartView
import br.com.fornaro.matafome.model.Restaurant
import br.com.fornaro.matafome.model.RestaurantDetail
import br.com.fornaro.matafome.repository.CartRepository
import javax.inject.Inject

open class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel() {

    fun getCartItems() = cartRepository.getAll()

    fun insertCartItem(restaurant: Restaurant, restaurantDetail: RestaurantDetail, quantity: Int) {
        val cartItem = CartItem(
                0L, restaurant.name, restaurant.deliveryFee, restaurant.imageUrl,
                restaurantDetail.name, restaurantDetail.price, restaurantDetail.imageUrl, quantity
        )
        cartRepository.insert(cartItem)
    }

    fun getCartView(): LiveData<CartView> {
        return Transformations.map(cartRepository.getAll()) { list ->
            var quantity = 0
            var totalPrice = 0f
            list.forEach {
                quantity += it.quantity
                totalPrice += it.price * it.quantity
            }
            CartView(quantity, totalPrice)
        }
    }

    fun decreaseQuantity(cartItem: CartItem) {
        cartItem.quantity--
        if (cartItem.quantity == 0) {
            cartRepository.delete(cartItem)
        } else {
            cartRepository.update(cartItem)
        }
    }

    fun increaseQuantity(cartItem: CartItem) {
        cartItem.quantity++
        cartRepository.update(cartItem)
    }
}