package br.com.fornaro.matafome.view

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import br.com.fornaro.matafome.di.component.AppComponent
import br.com.fornaro.matafome.di.component.DaggerAppComponent
import br.com.fornaro.matafome.di.module.AppModule

class MainApplication : Application() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.builder().appModule(AppModule(this)).build() }

    override fun onCreate() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate()
    }
}