package br.com.fornaro.matafome.api

import br.com.fornaro.matafome.model.RestaurantDetail
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantDetailApi {

    @GET("{id}")
    fun getRestaurantDetail(@Path("id") id: String): Observable<List<RestaurantDetail>>
}