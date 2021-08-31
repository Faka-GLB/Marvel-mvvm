package com.onboarding.marvel_mvvm.di

import com.onboarding.marvel_mvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}
