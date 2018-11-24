package br.com.fornaro.matafome.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val restaurantName: String,
    val deliveryFee: Float,
    val restaurantImage: String,
    val itemName: String,
    val price: Float,
    val itemImage: String,
    val quantity: Int
)