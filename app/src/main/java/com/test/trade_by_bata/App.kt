package com.test.trade_by_bata

import com.test.data_source.DataApp
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App @Inject constructor() : DataApp() {
}