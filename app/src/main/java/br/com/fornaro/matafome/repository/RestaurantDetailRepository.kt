package br.com.fornaro.matafome.repository

import br.com.fornaro.matafome.api.RestaurantDetailApi
import br.com.fornaro.matafome.model.RestaurantDetail
import io.reactivex.Observable

class RestaurantDetailRepository(private val api: RestaurantDetailApi) {

    fun getRestaurantDetail(restaurantId: String): Observable<List<RestaurantDetail>> {
        return api.getRestaurantDetail(restaurantId)
    }
}