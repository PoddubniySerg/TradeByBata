package com.test.data.util

import com.test.core.entities.Account
import com.test.core.model.AccountDto
import com.test.data.store.db.dto.AccountDtoDao
import javax.inject.Inject

class AccountUtils @Inject constructor() {

    fun convertToAccountDto(account: Account): AccountDto {
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

    fun convertToAccountDtoDao(account: Account): AccountDtoDao {
        return AccountDtoDao(
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