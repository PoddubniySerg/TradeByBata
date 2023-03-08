package com.test.trade_by_bata.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.test.domain.entities.GoodDetails
import com.test.trade_by_bata.model.GoodDetailsColor
import com.test.trade_by_bata.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
open class GoodDetailsViewModel @Inject constructor() : ViewModel() {

    private val _stateFlow = MutableStateFlow(State.COMPLETE)
    val stateFlow get() = _stateFlow.asStateFlow()

    private val _errorChannel = Channel<Exception>()
    val errorFlow get() = _errorChannel.receiveAsFlow()

    private var _goodDetails: GoodDetails? = null
    val goodDetails get() = _goodDetails

    private var _colors = mutableListOf<GoodDetailsColor>()
    val colors get() = _colors.toList()

    private var _total = 0.0
    val totalSum get() = _total

    fun setGoodDetails(goodDetails: GoodDetails) {
        _goodDetails = goodDetails
        for (color in goodDetails.colors) {
            _colors.add(GoodDetailsColor(color, false))
        }
    }

    fun addGood() {
        _total += goodDetails?.price ?: 0.0
    }

    fun removeGood() {
        _total -= goodDetails?.price ?: 0.0
    }
}