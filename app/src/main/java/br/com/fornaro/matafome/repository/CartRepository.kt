package br.com.fornaro.matafome.repository

import br.com.fornaro.matafome.database.dao.CartItemDao
import br.com.fornaro.matafome.database.entities.CartItem
import org.jetbrains.anko.doAsync

class CartRepository(private val dao: CartItemDao) {

    fun getAll() = dao.getAll()

    fun insert(cartItem: CartItem) {
        doAsync {
            val cartItemFromDb = dao.get(cartItem.restaurantName, cartItem.itemName)
            if (cartItemFromDb != null) {
                cartItemFromDb.quantity += cartItem.quantity
                dao.update(cartItemFromDb)
            } else {
                dao.insert(cartItem)
            }
        }
    }

    fun update(cartItem: CartItem) {
        doAsync { dao.update(cartItem) }
    }

    fun delete(cartItem: CartItem) {
        doAsync { dao.delete(cartItem) }
    }
}