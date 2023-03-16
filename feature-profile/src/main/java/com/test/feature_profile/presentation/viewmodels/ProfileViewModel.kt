package com.test.feature_profile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.core.util.State
import com.test.feature_profile.domain.model.requests.Photo
import com.test.feature_profile.domain.usecases.LogOutUseCase
import com.test.feature_profile.domain.usecases.SavePhotoUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class ProfileViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val savePhotoUseCase: SavePhotoUseCase
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(State.COMPLETE)
    val stateFlow get() = _stateFlow.asStateFlow()

    private val _errorChannel = Channel<Exception>()
    val errorFlow get() = _errorChannel.receiveAsFlow()

    private val _logOutFlow = MutableStateFlow(false)
    val logOutFlow = _logOutFlow.asStateFlow()

    private val _updatePhotoChannel = Channel<Boolean>()
    val updatePhotoStateFlow = _updatePhotoChannel.receiveAsFlow()

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

    fun savePhoto(uri: String, content: ByteArray) {
        viewModelScope.launch {
            try {
                _stateFlow.value = State.LOADING
                savePhotoUseCase
                    .execute(
                        Photo(uri, content)
                    )
                _updatePhotoChannel.send(true)
            } catch (ex: Exception) {
                _errorChannel.send(ex)
            } finally {
                _stateFlow.value = State.COMPLETE
            }
        }
    }
}