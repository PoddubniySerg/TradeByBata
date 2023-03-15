package com.test.feature_auth.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.test.core.fragments.BindFragment
import com.test.core.model.AccountDto
import com.test.core.util.State
import com.test.core.util.navigate
import com.test.feature_auth.R
import com.test.feature_auth.databinding.LoginFragmentBinding
import com.test.feature_auth.di.DaggerLoginComponent
import com.test.feature_auth.presentation.exceptions.InvalidFirstNameException
import com.test.feature_auth.presentation.exceptions.InvalidPasswordException
import com.test.feature_auth.presentation.exceptions.LoginException
import com.test.feature_auth.presentation.viewmodels.LoginViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginFragment : BindFragment<LoginFragmentBinding>(LoginFragmentBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel> {
        DaggerLoginComponent.create().loginViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()
        setFlowCollectors()
    }

    private fun setFlowCollectors() {
        viewModel.stateFlow.onEach { handleState(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.errorFlow.onEach { handleError(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.accountFlow.onEach { handleAccountFlow(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setClickListeners() {
        with(binding) {
            firstNameInputText.addTextChangedListener { clearAlarm() }
            passwordInputText.addTextChangedListener { clearAlarm() }
            loginButton.setOnClickListener { login() }
        }
    }

    private fun handleState(state: State) {
        when (state) {
            State.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            State.COMPLETE -> {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun handleError(exception: Exception) {
        when (exception) {
            is LoginException -> {
                binding.firstNameInputLayout.error = " "
                binding.passwordInputLayout.error = exception.message
            }
            is InvalidPasswordException -> {
                binding.passwordInputLayout.error = exception.message
            }
            is InvalidFirstNameException -> {
                binding.firstNameInputLayout.error = exception.message
            }
        }
    }

    private fun handleAccountFlow(account: AccountDto) {
        val args = Bundle().apply {
            putParcelable(resources.getString(com.test.core.R.string.account_key), account)
        }
        navigate(R.id.action_loginFragment_to_homeFragment, data = args)
    }

    private fun login() {
        clearAlarm()
        with(binding) {
            viewModel.login(firstNameInputText.text.toString(), passwordInputText.text.toString())
        }
    }

    private fun clearAlarm() {
        with(binding) {
            firstNameInputLayout.error = null
            passwordInputLayout.error = null
        }
    }
}