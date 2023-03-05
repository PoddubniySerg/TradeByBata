package com.test.trade_by_bata.presentation.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.test.domain.entities.Account
import com.test.trade_by_bata.R
import com.test.trade_by_bata.databinding.FragmentHomeBinding
import com.test.trade_by_bata.exceptions.HomeException
import com.test.trade_by_bata.model.AccountDto
import com.test.trade_by_bata.presentation.ui.MainActivity
import com.test.trade_by_bata.presentation.ui.adapters.HomeBrandsCollectionAdapter
import com.test.trade_by_bata.presentation.ui.adapters.HomeCategoriesAdapter
import com.test.trade_by_bata.presentation.ui.adapters.HomeFlashSaleCollectionAdapter
import com.test.trade_by_bata.presentation.ui.adapters.HomeLatestCollectionAdapter
import com.test.trade_by_bata.presentation.viewmodels.HomeViewModel
import com.test.trade_by_bata.statics.BundleKeys
import com.test.trade_by_bata.statics.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() :
    BindFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var categoriesAdapter: HomeCategoriesAdapter
    private lateinit var latestAdapter: HomeLatestCollectionAdapter
    private lateinit var flashSaleAdapter: HomeFlashSaleCollectionAdapter
    private lateinit var brandAdapter: HomeBrandsCollectionAdapter
    private lateinit var account: Account

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategories()
        viewModel.getData()
        handleArguments()
        initAdapters()
        setClickListeners()
        setFlowCollectors()
        bind()
    }

    private fun handleArguments() {
        if (arguments != null) {
            account = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requireArguments().getParcelable(BundleKeys.ACCOUNT_KEY, AccountDto::class.java)
            } else {
                requireArguments().getParcelable(BundleKeys.ACCOUNT_KEY)
            } ?: throw HomeException("Home hasn't account data")

            (requireActivity() as MainActivity).account = account
        } else account =
            (requireActivity() as MainActivity).account
        println()
    }

    private fun initAdapters() {
        categoriesAdapter =
            HomeCategoriesAdapter { category -> viewModel.onCategoryClick(category) }
        binding.categories.adapter = categoriesAdapter

        latestAdapter = HomeLatestCollectionAdapter(
            { good -> viewModel.addToCart(good) },
            { good -> viewModel.onGoodClick(good) }
        )
        binding.latestCollection.adapter = latestAdapter

        flashSaleAdapter = HomeFlashSaleCollectionAdapter(
            { good -> viewModel.addToCart(good) },
            { good -> viewModel.addToFavourites(good) },
            { good -> viewModel.onGoodClick(good) }
        )
        binding.flashSaleCollection.adapter = flashSaleAdapter

        brandAdapter = HomeBrandsCollectionAdapter()
        binding.brandsCollection.adapter = brandAdapter
    }

    private fun setClickListeners() {
        with(binding) {
            mainMenu.setOnClickListener { }
            locationButton.setOnClickListener { }
            searchButton.setOnClickListener { }
            viewAllLatestButton.setOnClickListener { }
            viewAllFlashSaleButton.setOnClickListener { }
            viewAllBrandsButton.setOnClickListener { }
        }
    }

    private fun setFlowCollectors() {
        viewModel.stateFlow.onEach { handleState(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.errorFlow.onEach { handleError(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.categoriesFlow
            .onEach { categoriesAdapter.submitList(it) }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.brandsFlow
            .onEach { brandAdapter.submitList(it) }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.latestFlow.combine(viewModel.flashSaleFlow) { latest, sale ->
            if (!latest.isEmpty() && !sale.isEmpty()) {
                latestAdapter.submitList(latest)
                flashSaleAdapter.submitList(sale)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun bind() {
        with(binding) {
            Glide.with(requireContext())
                .load(account.photoUrl)
                .placeholder(R.drawable.home_photo_place_holder)
                .into(photo)
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
//        Todo handle
    }
}