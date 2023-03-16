package com.test.feature_page1.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.core.entities.Brand
import com.test.core.entities.FlashSaleGood
import com.test.core.entities.GoodDetails
import com.test.core.entities.LatestGood
import com.test.core.util.State
import com.test.feature_page1.domain.model.requests.SearchRequest
import com.test.feature_page1.domain.usecases.*
import com.test.feature_page1.presentation.model.Category
import com.test.feature_page1.util.CategoriesUtil
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

open class HomeViewModel @Inject constructor(
    private val getBrandsUseCase: GetBrandsUseCase,
    private val getLatestGoodsUseCase: GetLatestGoodsUseCase,
    private val getFlashSaleGoodsUseCase: GetFlashSaleGoodsUseCase,
    private val getSearchKeyWordsUseCase: GetSearchKeyWordsUseCase,
    private val getDetailsGoodUseCase: GetDetailsGoodUseCase
) : ViewModel() {

    private val _categoriesSource = CategoriesUtil()

    private val _stateFlow = MutableStateFlow(State.COMPLETE)
    val stateFlow get() = _stateFlow.asStateFlow()

    private val _errorChannel = Channel<Exception>()
    val errorFlow get() = _errorChannel.receiveAsFlow()

    private val _categoriesChannel = Channel<List<Category>>()
    val categoriesFlow = _categoriesChannel.receiveAsFlow()

    private val _brandsChannel = Channel<List<Brand>>()
    val brandsFlow = _brandsChannel.receiveAsFlow()

    private val _latestChannel = Channel<List<LatestGood>>()
    val latestFlow = _latestChannel.receiveAsFlow()

    private val _flashSaleChannel = Channel<List<FlashSaleGood>>()
    val flashSaleFlow = _flashSaleChannel.receiveAsFlow()

    private val _goodDetailsChannel = Channel<GoodDetails>()
    val goodDetailsFlow = _goodDetailsChannel.receiveAsFlow()

    private var brands: List<Brand>? = null
    private var latest: List<LatestGood>? = null
    private var flashSales: List<FlashSaleGood>? = null

    fun getCategories() {
        viewModelScope.launch {
            _categoriesChannel.send(_categoriesSource.categories)
        }
    }

    fun getData() {
        viewModelScope.launch {
            try {
                _stateFlow.value = State.LOADING
                getBrands()
                getLatest()
                getFlashSale()
            } catch (e: Exception) {
                _errorChannel.send(e)
            } finally {
                _stateFlow.value = State.COMPLETE
            }
        }
    }

    fun getSearchKeyWords(input: String): List<String> {
        println("Search request")
        var results: List<String>? = null
        runBlocking {
            try {
                results = getSearchKeyWordsUseCase.execute(SearchRequest(input)).keyWords
            } catch (e: Exception) {
                _errorChannel.send(e)
            }
        }
        return results ?: emptyList()
    }

    fun onCategoryClick(category: Category) {}

    fun addToCart(good: LatestGood) {}

    fun addToFavourites(good: LatestGood) {}

    fun onGoodClick(good: LatestGood) {
        viewModelScope.launch {
            try {
                _stateFlow.value = State.LOADING
                _goodDetailsChannel.send(getDetailsGoodUseCase.execute().good)
            } catch (e: Exception) {
                _errorChannel.send(e)
            } finally {
                _stateFlow.value = State.COMPLETE
            }
        }
    }

    private suspend fun getBrands() {
        if (brands == null) brands = getBrandsUseCase.execute().brands
        brands?.let { _brandsChannel.send(it) }
    }

    private suspend fun getLatest() {
        if (latest == null) latest = getLatestGoodsUseCase.execute().goods
        latest?.let { _latestChannel.send(it) }
    }

    private suspend fun getFlashSale() {
        if (flashSales == null) flashSales = getFlashSaleGoodsUseCase.execute().goods
        flashSales?.let { _flashSaleChannel.send(it) }
    }
}