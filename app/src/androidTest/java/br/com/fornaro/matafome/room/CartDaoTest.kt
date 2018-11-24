package br.com.fornaro.matafome.room

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.fornaro.matafome.database.AppDatabase
import br.com.fornaro.matafome.database.dao.CartItemDao
import br.com.fornaro.matafome.extensions.getTheValue
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CartDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var dao: CartItemDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.cartItemDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testCreateCartItem() {
        val cartItem = createCartItem()
        dao.insert(cartItem)

        val cartItemFromDb = dao.getAll().getTheValue()[0]
        assertEquals(cartItem, cartItemFromDb)
    }

    @Test
    fun testCreateCartItems() {
        val cartItems = createCartItems()
        dao.insert(cartItems)

        val cartItemsFromDb = dao.getAll().getTheValue()
        assertEquals(cartItems, cartItemsFromDb)
    }
}