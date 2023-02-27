package com.test.trade_by_bata.util

import com.test.domain.entity.Account
import com.test.trade_by_bata.model.AccountDto
import javax.inject.Inject

class AccountUtil @Inject constructor() {

    fun convertAccountToDto(account: Account): AccountDto {
        return AccountDto(
            account.id,
            account.firstName,
            account.lastName,
            account.password,
            account.photoUrl,
            account.email,
            account.balance
        )
    }
}