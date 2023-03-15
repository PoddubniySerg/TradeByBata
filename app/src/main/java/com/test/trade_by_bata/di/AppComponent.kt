package com.test.trade_by_bata.di

import com.test.data.di.DataModule
import com.test.data.di.DataScope
import com.test.feature_auth.di.LoginComponent
import com.test.feature_page1.di.PageOneComponent
import com.test.feature_sign_in.di.SignInComponent
import dagger.Component
import javax.inject.Scope

@Component(modules = [DataModule::class])
@DataScope
interface AppComponent {

    fun signInComponent(): SignInComponent

    fun loginComponent(): LoginComponent

    fun pageOneComponent(): PageOneComponent
}

@Scope
annotation class AppScope