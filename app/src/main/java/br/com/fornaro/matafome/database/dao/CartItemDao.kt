package br.com.fornaro.matafome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.fornaro.matafome.database.entities.CartItem

@Dao
interface CartItemDao {

    @Query("SELECT * FROM CartItem")
    fun getAll(): LiveData<List<CartItem>>

    @Insert
    fun insert(cartItem: CartItem)

    @Delete
    fun delete(cartItem: CartItem)

    @Update
    fun update(cartItem: CartItem)
}