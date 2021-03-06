package br.com.fornaro.matafome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.fornaro.matafome.database.entities.CartItem

@Dao
interface CartItemDao {

    @Query("SELECT * FROM CartItem WHERE restaurantName = :restaurantName AND itemName = :itemName")
    fun get(restaurantName: String, itemName: String): CartItem?

    @Query("SELECT * FROM CartItem ORDER BY restaurantName")
    fun getAll(): LiveData<List<CartItem>>

    @Insert
    fun insert(cartItem: CartItem)

    @Insert
    fun insert(cartItem: List<CartItem>)

    @Delete
    fun delete(cartItem: CartItem)

    @Update
    fun update(cartItem: CartItem)

    @Query("DELETE FROM cartitem")
    fun clear()
}