package br.com.fornaro.matafome.di.module

import br.com.fornaro.matafome.database.dao.CartItemDao
import br.com.fornaro.matafome.repository.CartRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CartModule {

    @Singleton
    @Provides
    fun provideCartRepository(dao: CartItemDao) = CartRepository(dao)
}