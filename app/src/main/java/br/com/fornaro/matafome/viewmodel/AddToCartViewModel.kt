package br.com.fornaro.matafome.viewmodel

import androidx.databinding.ObservableField
import br.com.fornaro.matafome.model.Restaurant
import br.com.fornaro.matafome.model.RestaurantDetail
import br.com.fornaro.matafome.repository.CartRepository
import javax.inject.Inject

class AddToCartViewModel @Inject constructor(cartRepository: CartRepository) :
        CartViewModel(cartRepository) {

    val quantity = ObservableField(1)

    fun decreaseQuantity() {
        quantity.get()?.let {
            if (it > 1) {
                quantity.set(quantity.get()?.minus(1))
            }
        }
    }

    fun increaseQuantity() {
        quantity.set(quantity.get()?.plus(1))
    }

    fun insertCartItem(restaurant: Restaurant, restaurantDetail: RestaurantDetail) {
        insertCartItem(restaurant, restaurantDetail, quantity.get()!!)
    }
}