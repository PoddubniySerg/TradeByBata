package com.test.feature_page1.di

import com.test.feature_page1.presentation.viewmodels.HomeViewModelFactory
import dagger.Subcomponent
import javax.inject.Scope

@Subcomponent(modules = [PageOneModule::class])
@PageOneScope
interface PageOneComponent {

    fun homeViewModelFactory(): HomeViewModelFactory
}

interface PageOneComponentProvider {
    fun pageOneComponent(): PageOneComponent
}

@Scope
annotation class PageOneScope