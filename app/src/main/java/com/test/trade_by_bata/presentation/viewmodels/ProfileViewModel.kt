package com.test.trade_by_bata.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.usecases.LogOutUseCase
import com.test.trade_by_bata.statics.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ProfileViewModel @Inject constructor() : ViewModel() {

    @Inject
    protected lateinit var logOutUseCase: LogOutUseCase

    private val _stateFlow = MutableStateFlow(State.COMPLETE)
    val stateFlow get() = _stateFlow.asStateFlow()

    private val _logOutFlow = MutableStateFlow(false)
    val logOutFlow = _logOutFlow.asStateFlow()

    fun logOut() {
        viewModelScope.launch {
            try {
                _stateFlow.value = State.LOADING
                logOutUseCase.execute()
                _logOutFlow.value = true
            } catch (ex: Exception) {
//            todo exception handle
            } finally {
                _stateFlow.value = State.COMPLETE
            }
        }
    }
}