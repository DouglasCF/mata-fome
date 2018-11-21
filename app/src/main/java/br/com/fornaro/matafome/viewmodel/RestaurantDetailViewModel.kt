package br.com.fornaro.matafome.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fornaro.matafome.common.Resource
import br.com.fornaro.matafome.model.RestaurantDetail
import br.com.fornaro.matafome.repository.RestaurantDetailRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RestaurantDetailViewModel(private val restaurantDetailRepository: RestaurantDetailRepository) : ViewModel() {

    private var restaurantDetailLiveData: MutableLiveData<Resource<List<RestaurantDetail>>>? = null
    private val disposable = CompositeDisposable()

    fun getRestaurantDetail(restaurantId: String): LiveData<Resource<List<RestaurantDetail>>> {
        restaurantDetailLiveData?.let { return it }
        restaurantDetailLiveData = MutableLiveData()
        return restaurantDetailLiveData!!.apply {
            disposable.add(
                restaurantDetailRepository.getRestaurantDetail(restaurantId)
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