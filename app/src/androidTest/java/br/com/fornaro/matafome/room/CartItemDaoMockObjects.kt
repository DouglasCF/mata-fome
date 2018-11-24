package br.com.fornaro.matafome.room

import br.com.fornaro.matafome.database.entities.CartItem

fun createCartItem() =
    CartItem(
        1, "Restaurant Name", 10f, "",
        "Item Name", 20f, "", 2
    )

fun createCartItems() = listOf(
    CartItem(
        1, "Restaurant Name", 10f, "",
        "Item Name", 20f, "", 2
    ),
    CartItem(
        2, "Restaurant Name 2", 10f, "",
        "Item Name 2", 20f, "", 2
    )
)