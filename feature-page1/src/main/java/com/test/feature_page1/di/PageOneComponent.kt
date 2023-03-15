package com.test.feature_page1.di

import com.test.feature_page1.presentation.viewmodels.HomeViewModelFactory
import dagger.Component

@Component(modules = [PageOneModule::class])
interface PageOneComponent {

    fun homeViewModelFactory(): HomeViewModelFactory
}