package br.com.fornaro.matafome.di.component

import br.com.fornaro.matafome.di.module.*
import br.com.fornaro.matafome.view.restaurantdetail.RestaurantDetailFragment
import br.com.fornaro.matafome.view.restaurants.RestaurantsFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AppModule::class, NetworkModule::class, RestaurantModule::class, RestaurantDetailModule::class,
        ViewModelFactoryModule::class, ViewModelModule::class]
)
interface AppComponent {

    fun inject(fragment: RestaurantsFragment)
    fun inject(fragment: RestaurantDetailFragment)
}