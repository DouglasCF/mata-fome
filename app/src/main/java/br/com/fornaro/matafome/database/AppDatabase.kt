package br.com.fornaro.matafome.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fornaro.matafome.database.dao.CartItemDao
import br.com.fornaro.matafome.database.entities.CartItem

@Database(entities = [CartItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao
}