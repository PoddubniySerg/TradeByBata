package com.test.core.viewmodels

import androidx.lifecycle.ViewModel
import com.test.core.entities.Account
import javax.inject.Inject

open class AccountSourceViewModel @Inject constructor() : ViewModel() {

    private var _account: Account? = null
    val account get() = _account!!

    fun setAccount(account: Account) {
        _account = account
    }
}