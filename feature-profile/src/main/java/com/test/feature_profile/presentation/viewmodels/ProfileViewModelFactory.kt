package com.test.feature_profile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ProfileViewModelFactory @Inject constructor(private val viewModel: ProfileViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return viewModel as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }
    }
}