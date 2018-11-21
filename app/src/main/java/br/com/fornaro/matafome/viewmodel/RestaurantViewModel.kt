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

class RestaurantViewModel(private val restaurantRepository: RestaurantRepository) : ViewModel() {

    private var restaurantsLiveData: MutableLiveData<Resource<List<Restaurant>>>? = null
    private val disposable = CompositeDisposable()

    fun getRestaurants(): LiveData<Resource<List<Restaurant>>> {
        restaurantsLiveData?.let { return it }
        restaurantsLiveData = MutableLiveData()
        return restaurantsLiveData!!.apply {
            disposable.add(restaurantRepository.getRestaurants()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { value = Resource.loading() }
                    .subscribe({ value = Resource.success(it) },
                            { value = Resource.error(it.message) })
            )
        }
    }

    override fun onCleared() {
        disposable.clear()
    }
}