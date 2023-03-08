package com.test.trade_by_bata.presentation.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.test.trade_by_bata.R
import com.test.trade_by_bata.databinding.FragmentGoodDetailsBinding
import com.test.trade_by_bata.databinding.GoodDetailsPosterMediatorDefaultBinding
import com.test.trade_by_bata.databinding.GoodDetailsPosterMediatorSelectedBinding
import com.test.trade_by_bata.model.GoodDetailsDto
import com.test.trade_by_bata.presentation.ui.adapters.GoodDetailsColorsAdapter
import com.test.trade_by_bata.presentation.ui.adapters.GoodDetailsPostersViewPagerAdapter
import com.test.trade_by_bata.presentation.viewmodels.GoodDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GoodDetailsFragment @Inject constructor() :
    BindFragment<FragmentGoodDetailsBinding>(FragmentGoodDetailsBinding::inflate) {

    private val viewModel by viewModels<GoodDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val good = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(
                resources.getString(R.string.good_details_key),
                GoodDetailsDto::class.java
            )
        } else {
            arguments?.getParcelable(resources.getString(R.string.good_details_key))
        } ?: findNavController().popBackStack()
        viewModel.setGoodDetails(good as GoodDetailsDto)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerInit()
        with(binding) {
            goodName.text = viewModel.goodDetails?.name ?: ""
            goodPrice.text = String.format("$ %.2f", viewModel.goodDetails?.price ?: 0.0)
            goodDescription.text = viewModel.goodDetails?.description ?: ""
            goodRating.text = String.format("%.1f", viewModel.goodDetails?.rating ?: 0.0)
            reviewsCount.text =
                String.format("%d reviews", viewModel.goodDetails?.reviewsCount ?: 0)
            listColors.adapter = GoodDetailsColorsAdapter()
            (listColors.adapter as GoodDetailsColorsAdapter).submitList(viewModel.colors)
            total.text = String.format("# %.2f", 0.0)
            goodsQuatityMinusButton.isEnabled = false
            goodsQuatityMinusButton.setOnClickListener {
                viewModel.removeGood()
                if (viewModel.totalSum <= 0) it.isEnabled = false
            }
            goodsQuantityPlusButton.setOnClickListener {
                viewModel.addGood()
                goodsQuatityMinusButton.isEnabled = true
            }
        }
    }

    private fun viewPagerInit() {
        val urlList = viewModel.goodDetails?.imageUrls ?: return
        val viewPager = binding.posterViewPager
        viewPager.adapter =
            GoodDetailsPostersViewPagerAdapter(urlList)
        val tabLayout = binding.postersTabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            val customBinding =
                if (tab.isSelected) GoodDetailsPosterMediatorSelectedBinding.inflate(layoutInflater)
                else GoodDetailsPosterMediatorDefaultBinding.inflate(layoutInflater)
            Glide.with(requireContext()).load(urlList[pos]).into(
                if (customBinding is GoodDetailsPosterMediatorSelectedBinding) customBinding.picture
                else (customBinding as GoodDetailsPosterMediatorDefaultBinding).picture
            )
            tab.customView = binding.root
        }.attach()
    }
}