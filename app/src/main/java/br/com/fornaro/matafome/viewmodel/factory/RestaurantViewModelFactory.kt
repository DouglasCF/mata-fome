package br.com.fornaro.matafome.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.fornaro.matafome.repository.RestaurantRepository
import br.com.fornaro.matafome.viewmodel.RestaurantViewModel
import javax.inject.Inject

class RestaurantViewModelFactory @Inject constructor(private val restaurantRepository: RestaurantRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantViewModel::class.java)) {
            return RestaurantViewModel(restaurantRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}