package br.com.fornaro.matafome.api

import br.com.fornaro.matafome.model.Restaurant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantApi {

    @GET("{id}")
    fun getRestaurants(@Path("id") id: String): Observable<List<Restaurant>>
}