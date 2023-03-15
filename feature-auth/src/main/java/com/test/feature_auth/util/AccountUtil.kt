package com.test.feature_auth.util

import com.test.core.entities.Account
import com.test.core.model.AccountDto
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