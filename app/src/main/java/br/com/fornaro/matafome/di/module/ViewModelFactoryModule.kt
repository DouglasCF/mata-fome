package br.com.fornaro.matafome.di.module

import androidx.lifecycle.ViewModelProvider
import br.com.fornaro.matafome.viewmodel.factory.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}