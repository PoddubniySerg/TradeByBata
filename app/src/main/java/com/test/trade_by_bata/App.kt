package com.test.trade_by_bata

import com.test.data.DataApp
import com.test.feature_auth.di.LoginComponent
import com.test.feature_auth.di.LoginComponentProvider
import com.test.feature_page1.di.PageOneComponent
import com.test.feature_page1.di.PageOneComponentProvider
import com.test.feature_sign_in.di.SignInComponent
import com.test.feature_sign_in.di.SignInComponentProvider
import com.test.trade_by_bata.di.AppComponent
import com.test.trade_by_bata.di.DaggerAppComponent

class App : DataApp(),
    SignInComponentProvider,
    PageOneComponentProvider,
    LoginComponentProvider {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    override fun signInComponent(): SignInComponent {
        return this.appComponent.signInComponent()
    }

    override fun pageOneComponent(): PageOneComponent {
        return this.appComponent.pageOneComponent()
    }

    override fun loginComponent(): LoginComponent {
        return this.appComponent.loginComponent()
    }
}