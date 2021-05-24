package com.example.foodbox.utils

import android.app.Application
import com.example.foodbox.di.persistenceDataModule
import com.example.foodbox.di.viewModelModule
import io.paperdb.Paper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class FoodBox:Application(){
    override fun onCreate() {
        super.onCreate()
        Paper.init(applicationContext)
        startKoin {
            androidContext(this@FoodBox)
            androidFileProperties()
            modules(
                listOf(
                    viewModelModule, persistenceDataModule
                )
            )
        }
    }
}