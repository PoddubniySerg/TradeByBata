package com.test.feature_auth.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.core.model.AccountDto
import com.test.core.util.State
import com.test.feature_auth.domain.model.LoginObject
import com.test.feature_auth.domain.usecases.LoginUseCase
import com.test.feature_auth.presentation.exceptions.InvalidFirstNameException
import com.test.feature_auth.presentation.exceptions.InvalidPasswordException
import com.test.feature_auth.presentation.exceptions.LoginException
import com.test.feature_auth.util.AccountUtil
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val accountUtil: AccountUtil
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(State.COMPLETE)
    val stateFlow get() = _stateFlow.asStateFlow()

    private val _errorChannel = Channel<Exception>()
    val errorFlow get() = _errorChannel.receiveAsFlow()

    private val _accountChannel = Channel<AccountDto>()
    val accountFlow get() = _accountChannel.receiveAsFlow()

    fun login(firstName: String, password: String) {
        viewModelScope.launch {
            try {
                _stateFlow.value = State.LOADING
                val isFirstNameValid = isFirstNameValid(firstName)
//                val isPasswordValid = isPasswordValid(password)
                val isPasswordValid = true
                if (isFirstNameValid && isPasswordValid) {
                    val account = loginUseCase.execute(
                        LoginObject(
                            firstName,
                            password
                        )
                    ).account ?: throw LoginException("Invalid login or password")
                    _accountChannel.send(
                        accountUtil.convertAccountToDto(account)
                    )
                }
            } catch (ex: Exception) {
                _errorChannel.send(ex)
            } finally {
                _stateFlow.value = State.COMPLETE
            }
        }
    }

    private fun isPasswordValid(password: String?): Boolean {
        return try {
            val isValid =
                password != null
                        && password.isNotEmpty()
                        && password.isNotBlank()
                        && !password.contains(' ')
            viewModelScope.launch {
                if (!isValid) _errorChannel.send(
                    InvalidPasswordException("Invalid password")
                )
            }
            isValid
        } catch (ex: Exception) {
            false
        }
    }

    private fun isFirstNameValid(firstName: String?): Boolean {
        return try {
            val isValid =
                firstName != null
                        && firstName.isNotEmpty()
                        && firstName.isNotBlank()
                        && !firstName.contains(' ')
            viewModelScope.launch {
                if (!isValid)
                    _errorChannel.send(InvalidFirstNameException("Unable value for first name"))
            }
            isValid
        } catch (ex: Exception) {
            false
        }
    }
}