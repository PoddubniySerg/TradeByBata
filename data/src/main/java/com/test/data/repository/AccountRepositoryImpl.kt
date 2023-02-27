package com.test.data.repository

import com.test.data.DataApp
import com.test.data.store.db.AccountsDao
import com.test.data.store.db.dto.AccountDto
import com.test.data.store.sharedpreferences.DeviceMemory
import com.test.domain.entity.Account
import com.test.domain.model.params.*
import com.test.domain.repository.AccountRepository

open class AccountRepositoryImpl : AccountRepository {

    private val deviceMemory: DeviceMemory = DeviceMemory()

    private val accountsDao: AccountsDao = DataApp.getDataBase().accountDao()

    override suspend fun getCurrentUserId(): Int? {
        return deviceMemory.getCurrentUserId()
    }

    override suspend fun saveUserId(param: SaveUserIdParams) {
        deviceMemory.saveCurrentUserId(param.id)
    }

    override suspend fun removeUserId() {
        deviceMemory.removeCurrentUserId()
    }

    override suspend fun getUserById(param: GetUserByIdParams): Account? {
        return accountsDao.getUserById(param.id)
    }

    override suspend fun getUserByMetrics(param: UserMetricParams): Account? {
        return accountsDao.getUserByMetrics(param.firstName, param.email)
    }

    override suspend fun create(param: CreateAccountParams): Account? {
        val account = AccountDto(
            firstName = param.firstName,
            lastName = param.lastName,
            password = "",
            photoUrl = null,
            email = param.email,
            balance = 0
        )
        accountsDao.save(
            account
        )
        return accountsDao.getUserByMetrics(account.firstName, account.email)
    }

    override suspend fun login(param: LoginParams): Account? {
//        todo handle
        return null
    }

    override suspend fun remove(param: RemoveAccountParams) {
        accountsDao.remove(param.id)
    }
}