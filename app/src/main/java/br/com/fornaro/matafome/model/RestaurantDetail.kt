package br.com.fornaro.matafome.model

import com.google.gson.annotations.SerializedName

data class RestaurantDetail(
        val name: String,
        val description: String,
        val price: Float,
        @SerializedName("image_url") val imageUrl: String?
)