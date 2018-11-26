package br.com.fornaro.matafome.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.fornaro.matafome.database.entities.CartItem
import br.com.fornaro.matafome.model.CartView
import br.com.fornaro.matafome.model.Restaurant
import br.com.fornaro.matafome.model.RestaurantDetail
import br.com.fornaro.matafome.repository.CartRepository
import br.com.fornaro.matafome.view.cart.CartMessage
import javax.inject.Inject

open class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel() {

    val totalPrice = ObservableField<Float>(0f)
    val paymentMethod = ObservableField<String>()

    private val messageLiveData = MutableLiveData<CartMessage>()

    fun getMessage() = messageLiveData

    fun getCartItems(): LiveData<List<CartItem>> = Transformations.map(cartRepository.getAll()) { list ->
        var totalValue = 0f
        list.forEach {
            totalValue += it.price * it.quantity
        }
        val restaurantList = list.distinctBy { it.restaurantName }
        restaurantList.forEach {
            totalValue += it.deliveryFee
        }

        totalPrice.set(totalValue)
        list
    }

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
            val restaurantList = list.distinctBy { it.restaurantName }
            restaurantList.forEach {
                totalPrice += it.deliveryFee
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

    fun setPaymentMethod(payment: String) {
        paymentMethod.set(payment)
    }

    fun finishOrder() {
        if (paymentMethod.get().isNullOrEmpty()) {
            messageLiveData.value = CartMessage.CHOOSE_PAYMENT_METHOD
        } else {
            cartRepository.finishOrder(paymentMethod.get())
            messageLiveData.value = CartMessage.ORDER_FINISHED
        }
    }
}