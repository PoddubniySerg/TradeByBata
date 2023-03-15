package com.test.feature_auth.di

import com.test.feature_auth.presentation.viewmodels.LoginViewModelFactory
import dagger.Subcomponent
import javax.inject.Scope

@Subcomponent(modules = [LoginDataModule::class])
@LoginScope
interface LoginComponent {

    fun loginViewModelFactory(): LoginViewModelFactory
}

interface LoginComponentProvider {
    fun loginComponent(): LoginComponent
}

@Scope
annotation class LoginScope