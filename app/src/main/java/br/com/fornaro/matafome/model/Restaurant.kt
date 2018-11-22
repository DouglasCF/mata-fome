package br.com.fornaro.matafome.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
    val id: String,
    val name: String,
    val type: String,
    val description: String,
    @SerializedName("delivery_fee") val deliveryFee: Float,
    @SerializedName("image_url") val imageUrl: String?,
    val rating: Double
) : Parcelable