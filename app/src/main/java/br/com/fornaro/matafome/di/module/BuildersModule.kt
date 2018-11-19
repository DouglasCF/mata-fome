package br.com.fornaro.matafome.di.module

import br.com.fornaro.matafome.view.restaurants.RestaurantsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCryptocurrenciesActivity(): RestaurantsFragment
}