package br.com.fornaro.matafome.repository

import br.com.fornaro.matafome.api.RestaurantApi
import br.com.fornaro.matafome.model.Restaurant
import io.reactivex.Observable
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val api: RestaurantApi) {

    fun getRestaurants(): Observable<List<Restaurant>> {
        return api.getRestaurants("5bf306382f00003939cfa367")
    }
}