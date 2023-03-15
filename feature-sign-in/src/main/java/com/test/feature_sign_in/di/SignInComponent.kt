package com.test.feature_sign_in.di

import com.test.feature_sign_in.presentation.viewmodels.SignInViewModelFactory
import dagger.Component

@Component(modules = [SignInDataModule::class])
interface SignInComponent {

    fun signInViewModelFactory(): SignInViewModelFactory
}