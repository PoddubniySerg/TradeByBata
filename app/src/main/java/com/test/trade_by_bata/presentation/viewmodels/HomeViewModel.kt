package com.test.trade_by_bata.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.entities.Brand
import com.test.domain.entities.FlashSaleGood
import com.test.domain.entities.LatestGood
import com.test.domain.model.requests.SearchRequest
import com.test.domain.usecases.GetBrandsUseCase
import com.test.domain.usecases.GetFlashSaleGoodsUseCase
import com.test.domain.usecases.GetLatestGoodsUseCase
import com.test.domain.usecases.GetSearchKeyWordsUseCase
import com.test.trade_by_bata.model.Category
import com.test.trade_by_bata.util.State
import com.test.trade_by_bata.util.CategoriesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor() : ViewModel() {

    @Inject
    protected lateinit var getBrandsUseCase: GetBrandsUseCase

    @Inject
    protected lateinit var getLatestGoodsUseCase: GetLatestGoodsUseCase

    @Inject
    protected lateinit var getFlashSaleGoodsUseCase: GetFlashSaleGoodsUseCase

    @Inject
    protected lateinit var getSearchKeyWordsUseCase: GetSearchKeyWordsUseCase

    private val _categoriesSource = CategoriesUtil()

    private val _stateFlow = MutableStateFlow(State.COMPLETE)
    val stateFlow get() = _stateFlow.asStateFlow()

    private val _searchWordsMutableStateFlow = MutableStateFlow(State.COMPLETE)
    val searchWordsStateFlow = _searchWordsMutableStateFlow.asStateFlow()

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

    private val _searchWordsChannel = Channel<List<String>>()
    val searchWordsFlow = _searchWordsChannel.receiveAsFlow()

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

    fun onGoodClick(good: LatestGood) {}

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