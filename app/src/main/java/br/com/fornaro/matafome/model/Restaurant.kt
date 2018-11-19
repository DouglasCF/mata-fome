package br.com.fornaro.matafome.model

import com.google.gson.annotations.SerializedName

data class Restaurant(
    val id: String,
    val name: String,
    val type: String,
    val description: String,
    @SerializedName("delivery_fee") val deliveryFee: Double,
    @SerializedName("image_url") val image_url: String?,
    val rating: Double
)