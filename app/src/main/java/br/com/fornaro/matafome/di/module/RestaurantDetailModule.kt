package br.com.fornaro.matafome.di.module

import br.com.fornaro.matafome.api.RestaurantDetailApi
import br.com.fornaro.matafome.repository.RestaurantDetailRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RestaurantDetailModule {

    @Singleton
    @Provides
    fun provideRestaurantDetailApi(retrofit: Retrofit): RestaurantDetailApi = retrofit.create(RestaurantDetailApi::class.java)

    @Singleton
    @Provides
    fun provideRestaurantDetailRepository(api: RestaurantDetailApi) = RestaurantDetailRepository(api)
}