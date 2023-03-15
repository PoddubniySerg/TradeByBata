package com.test.feature_auth.di

import com.test.feature_auth.presentation.viewmodels.LoginViewModelFactory
import dagger.Component

@Component(modules = [LoginDataModule::class])
interface LoginComponent {

    fun loginViewModelFactory(): LoginViewModelFactory
}