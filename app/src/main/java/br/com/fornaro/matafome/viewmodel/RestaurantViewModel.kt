package br.com.fornaro.matafome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fornaro.matafome.common.Resource
import br.com.fornaro.matafome.model.Restaurant
import br.com.fornaro.matafome.repository.RestaurantRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RestaurantViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun getRestaurants(): LiveData<Resource<List<Restaurant>>> {
        val data = MutableLiveData<Resource<List<Restaurant>>>()

        disposables.add(restaurantRepository.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { data.value = Resource.loading() }
            .subscribe({ data.value = Resource.success(it) },
                { data.value = Resource.error(it.message) })
        )

        return data
    }

    override fun onCleared() {
        disposables.clear()
    }
}