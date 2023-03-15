package com.test.feature_sign_in.di

import com.test.feature_sign_in.presentation.viewmodels.SignInViewModelFactory
import dagger.Subcomponent
import javax.inject.Scope

@Subcomponent(modules = [SignInDataModule::class])
@SignInScope
interface SignInComponent {

    fun signInViewModelFactory(): SignInViewModelFactory
}

interface SignInComponentProvider {
    fun signInComponent(): SignInComponent
}

@Scope
annotation class SignInScope