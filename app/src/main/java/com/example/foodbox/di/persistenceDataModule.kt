package com.example.foodbox.di

import android.content.Context
import android.content.SharedPreferences
import com.example.foodbox.data.prefs.PreferenceManager
import com.example.foodbox.data.prefs.PreferenceManagerImp
import org.koin.dsl.module

val persistenceDataModule = module {
    single {
        provideSharedPreference(get(), "FoodBox_prefs")
    }
    single {
        providePreferenceManager(get())
    }
}

fun provideSharedPreference(context: Context, preferenceName: String): SharedPreferences {
    return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
}

fun providePreferenceManager(preferences: SharedPreferences): PreferenceManager =
    PreferenceManagerImp(preferences)