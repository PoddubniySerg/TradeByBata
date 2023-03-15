package com.test.trade_by_bata.di

import com.test.feature_auth.di.LoginComponent
import com.test.feature_page1.di.PageOneComponent
import com.test.feature_sign_in.di.SignInComponent
import com.test.trade_by_bata.presentation.ui.MainActivity
import dagger.Component

@Component(
    modules = [MainActivityModule::class],
    dependencies = [
        LoginComponent::class,
        SignInComponent::class,
        PageOneComponent::class
    ]
)
interface MainActivityComponent {

    fun injectMainActivity(mainActivity: MainActivity)
}