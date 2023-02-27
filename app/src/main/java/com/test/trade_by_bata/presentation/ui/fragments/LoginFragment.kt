package com.test.trade_by_bata.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.test.trade_by_bata.R
import com.test.trade_by_bata.databinding.FragmentLoginBinding
import com.test.trade_by_bata.exceptions.InvalidFirstNameException
import com.test.trade_by_bata.exceptions.InvalidPasswordException
import com.test.trade_by_bata.exceptions.LoginException
import com.test.trade_by_bata.model.AccountDto
import com.test.trade_by_bata.presentation.viewmodels.LoginViewModel
import com.test.trade_by_bata.statics.BundleKeys
import com.test.trade_by_bata.statics.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment @Inject constructor() :
    BindFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel>()

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
            putParcelable(BundleKeys.ACCOUNT_KEY, account)
        }
        findNavController().navigate(R.id.action_signInFragment_to_profileFragment, args)
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