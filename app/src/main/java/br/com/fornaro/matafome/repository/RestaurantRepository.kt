package br.com.fornaro.matafome.repository

import br.com.fornaro.matafome.api.RestaurantApi
import br.com.fornaro.matafome.model.Restaurant
import io.reactivex.Observable

class RestaurantRepository(private val api: RestaurantApi) {

    fun getRestaurants(): Observable<List<Restaurant>> {
        return api.getRestaurants("5bf99fbc3200007000f2256e")
    }
}