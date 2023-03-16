package com.test.feature_profile.di

import com.test.feature_profile.presentation.viewmodels.ProfileViewModelFactory
import dagger.Subcomponent
import javax.inject.Scope

@Subcomponent(modules = [ProfileDaggerModule::class])
@ProfileScope
interface ProfileComponent {

    fun profileViewModelFactory(): ProfileViewModelFactory
}

interface ProfileComponentProvider {
    fun profileComponent(): ProfileComponent
}

@Scope
annotation class ProfileScope