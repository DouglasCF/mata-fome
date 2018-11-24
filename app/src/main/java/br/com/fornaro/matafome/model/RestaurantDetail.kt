package br.com.fornaro.matafome.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestaurantDetail(
        val name: String,
        val description: String,
        val price: Float,
        @SerializedName("image_url") val imageUrl: String?
) : Parcelable