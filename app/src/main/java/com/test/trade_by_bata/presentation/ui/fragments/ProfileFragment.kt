package com.test.trade_by_bata.presentation.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.test.trade_by_bata.R
import com.test.trade_by_bata.databinding.FragmentProfileBinding
import com.test.trade_by_bata.exceptions.ProfileException
import com.test.trade_by_bata.model.AccountDto
import com.test.trade_by_bata.presentation.viewmodels.ProfileViewModel
import com.test.trade_by_bata.statics.BundleKeys
import com.test.trade_by_bata.statics.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment @Inject constructor() :
    BindFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind()
        setFlowCollectors()
        setClickListeners()
    }

    private fun bind() {

        val account = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(BundleKeys.ACCOUNT_KEY, AccountDto::class.java)
        } else {
            requireArguments().getParcelable(BundleKeys.ACCOUNT_KEY)
        } ?: throw ProfileException("Profile hasn't account data")

        with(binding) {
            with(toolbar) {
                setupWithNavController(findNavController())
                title = null
            }
            Glide.with(requireContext()).load(account.photoUrl)
                .placeholder(R.drawable.profile_photo_place_holder).into(photo)

            nameUserTextView.text = String.format("%s %s", account.firstName, account.lastName)

            val balance =
                if (account.balance == 0) 1593
                else account.balance
            balanceTextView.text = String.format("$ %d", balance)
        }
    }

    private fun setFlowCollectors() {
        viewModel.stateFlow.onEach { handleState(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.logOutFlow.onEach { handleLogOut(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setClickListeners() {
        with(binding) {
            changePhotoImageButton.setOnClickListener { changePhoto() }
            uploadItemButton.setOnClickListener { uploadItem() }
            tradeStoreButton.setOnClickListener { openTradeStore() }
            paymentMethodButton.setOnClickListener { openPayments() }
            tradeHistoryButton.setOnClickListener { openTradeHistory() }
            restorePurchaseButton.setOnClickListener { restorePurchase() }
            helpButton.setOnClickListener { help() }
            logOutButton.setOnClickListener { logOut() }
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

    private fun handleLogOut(isLogOut: Boolean) {
        if (isLogOut) findNavController().navigate(R.id.action_profileFragment_to_signInFragment)
    }

    private fun changePhoto() {
//        TODO
    }

    private fun uploadItem() {
//        TODO
    }

    private fun openTradeStore() {
//        TODO
    }

    private fun openPayments() {
//        TODO
    }

    private fun openTradeHistory() {
//        TODO
    }

    private fun restorePurchase() {
//        TODO
    }

    private fun help() {
//        TODO
    }

    private fun logOut() {
        viewModel.logOut()
    }
}