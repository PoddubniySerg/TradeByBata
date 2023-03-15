package com.test.feature_sign_in.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.test.core.fragments.BindFragment
import com.test.core.model.AccountDto
import com.test.core.util.State
import com.test.core.util.navigate
import com.test.feature_sign_in.R
import com.test.feature_sign_in.databinding.FragmentSignInBinding
import com.test.feature_sign_in.di.SignInComponentProvider
import com.test.feature_sign_in.exceptions.*
import com.test.feature_sign_in.presentation.viewmodels.SignInViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SignInFragment :
    BindFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val viewModel by viewModels<SignInViewModel> {
        (requireContext().applicationContext as SignInComponentProvider)
            .signInComponent()
            .signInViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setFlowCollectors()
        viewModel.getCurrentAccount()
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

            lastNameInputText.addTextChangedListener { clearAlarm() }

            emailInputText.addTextChangedListener { clearAlarm() }

            signInButton.setOnClickListener { signIn() }

            navigateToLoginButton.setOnClickListener { goToLogin() }

            googleAuthoriseButton.setOnClickListener { authGoogle() }

            appleAuthoriseButton.setOnClickListener { authApple() }
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

    private fun handleError(exception: java.lang.Exception) {
        when (exception) {
            is SignInException -> {
                binding.firstNameInputLayout.error = " "
                binding.lastNameInputLayout.error = " "
                binding.emailInputLayout.error = exception.message
            }
            is GoogleAuthorisedException -> {
                setDrawable(
                    binding.googleAuthoriseButton,
                    R.drawable.sign_in_google_account_error_foreground
                )
                binding.googleAuthoriseAlarm.text = exception.message
            }
            is AppleAuthorisedException -> {
                setDrawable(
                    binding.appleAuthoriseButton,
                    R.drawable.sign_in_apple_account_error_foreground
                )
                binding.appleAuthoriseAlarm.text = exception.message
            }
            is InvalidEmailException -> {
                binding.emailInputLayout.error = exception.message
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
        navigate(R.id.action_signInFragment_to_homeFragment, data = args)
    }

    private fun clearAlarm() {
        with(binding) {
            firstNameInputLayout.error = null
            lastNameInputLayout.error = null
            emailInputLayout.error = null
            googleAuthoriseAlarm.text = ""
            appleAuthoriseAlarm.text = ""
            setDrawable(googleAuthoriseButton, R.drawable.sign_in_google_account_icon)
            setDrawable(appleAuthoriseButton, R.drawable.sign_in_apple_account_icon)
        }
    }

    private fun setDrawable(imageView: AppCompatImageButton, resId: Int) {
        imageView
            .setImageDrawable(
                ResourcesCompat
                    .getDrawable(
                        resources,
                        resId,
                        null
                    )
            )
    }

    private fun signIn() {
        clearAlarm()
        viewModel.signIn(
            binding.firstNameInputText.text.toString(),
            binding.lastNameInputText.text.toString(),
            binding.emailInputText.text.toString()
        )
    }

    private fun goToLogin() {
        clearAlarm()
        findNavController().navigate(R.id.action_signInFragment_to_loginFragment)
    }

    private fun authGoogle() {
        clearAlarm()
        viewModel.signInGoogle()
    }

    private fun authApple() {
        clearAlarm()
        viewModel.signInApple()
    }
}