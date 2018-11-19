package br.com.fornaro.matafome.di.component

import br.com.fornaro.matafome.di.module.AppModule
import br.com.fornaro.matafome.di.module.BuildersModule
import br.com.fornaro.matafome.di.module.NetworkModule
import br.com.fornaro.matafome.di.module.RestaurantModule
import br.com.fornaro.matafome.view.restaurants.RestaurantsFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, NetworkModule::class, BuildersModule::class,RestaurantModule::class])
interface AppComponent {

    fun inject(fragment:RestaurantsFragment)
}