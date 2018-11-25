package br.com.fornaro.matafome.di.module

import androidx.lifecycle.ViewModel
import br.com.fornaro.matafome.di.mapkey.ViewModelKey
import br.com.fornaro.matafome.viewmodel.AddToCartViewModel
import br.com.fornaro.matafome.viewmodel.CartViewModel
import br.com.fornaro.matafome.viewmodel.RestaurantDetailViewModel
import br.com.fornaro.matafome.viewmodel.RestaurantViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantViewModel::class)
    abstract fun bindRestaurantViewModel(viewModel: RestaurantViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantDetailViewModel::class)
    abstract fun bindRestaurantDetailViewModel(viewModel: RestaurantDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddToCartViewModel::class)
    abstract fun bindAddToCartViewModel(viewModel: AddToCartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(viewModel: CartViewModel): ViewModel
}