package br.com.fornaro.matafome.di.module

import br.com.fornaro.matafome.api.RestaurantApi
import br.com.fornaro.matafome.repository.RestaurantRepository
import br.com.fornaro.matafome.viewmodel.factory.RestaurantViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RestaurantModule {

    @Singleton
    @Provides
    fun provideRestaurantApi(retrofit: Retrofit): RestaurantApi = retrofit.create(RestaurantApi::class.java)

    @Singleton
    @Provides
    fun provideRestaurantRepository(api: RestaurantApi) = RestaurantRepository(api)

    @Singleton
    @Provides
    fun provideRestaurantViewModelFactory(restaurantRepository: RestaurantRepository) =
        RestaurantViewModelFactory(restaurantRepository)
}