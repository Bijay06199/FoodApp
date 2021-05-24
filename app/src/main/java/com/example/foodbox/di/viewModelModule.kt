package com.example.foodbox.di

import com.example.foodbox.ui.main.MainViewModel
import com.example.foodbox.ui.main.home.HomeViewModel
import com.example.foodbox.ui.main.home.cart.CartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule:Module= module {

    viewModel { MainViewModel() }

    viewModel { HomeViewModel() }
    viewModel { CartViewModel() }


}