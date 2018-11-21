package br.com.fornaro.matafome.repository.restaurants

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.fornaro.matafome.api.RestaurantApi
import br.com.fornaro.matafome.repository.RestaurantRepository
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class RestaurantRepositoryUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val api: RestaurantApi = Mockito.mock(RestaurantApi::class.java)
    private val repository by lazy { RestaurantRepository(api) }

    @Test
    fun testRestaurantRepository_getRestaurants_completed() {
        Mockito.`when`(repository.getRestaurants())
            .thenReturn(Observable.just(emptyList()))

        repository.getRestaurants()
            .test()
            .assertComplete()
    }

    @Test
    fun testRestaurantRepository_getRestaurants_error() {
        val response = Throwable("Error")
        Mockito.`when`(repository.getRestaurants())
            .thenReturn(Observable.error(response))

        repository.getRestaurants()
            .test()
            .assertError(response)
    }

    @Test
    fun testRestaurantRepository_getRestaurants_response() {
        val response = listOf(restaurantPOJOmodel())
        Mockito.`when`(repository.getRestaurants())
            .thenReturn(Observable.just(response))

        repository.getRestaurants()
            .test()
            .assertValue(response)
    }
}