package com.test.data.repository

import com.test.core.model.AccountDto
import com.test.data.store.db.AccountsDao
import com.test.data.store.db.dto.AccountDtoDao
import com.test.data.store.files.FilesRepository
import com.test.data.store.sharedpreferences.SharedPreferences
import com.test.data.util.AccountUtils
import com.test.feature_auth.data.LoginAccountsRepository
import com.test.feature_sign_in.data.SignInAccountsRepository

class AccRepository(
    private val sharedPreferences: SharedPreferences,
    private val accountsDao: AccountsDao,
    private val fileStorage: FilesRepository,
    private val accountsUtils: AccountUtils
) : LoginAccountsRepository, SignInAccountsRepository {

    override suspend fun login(firstName: String, password: String): AccountDto? {
        return assertNull(accountsDao.login(firstName, password))
    }

    override suspend fun save(account: AccountDto) {
        accountsDao.save(accountsUtils.convertToAccountDtoDao(account))
    }

    override suspend fun update(account: AccountDto) {
        accountsDao.update(accountsUtils.convertToAccountDtoDao(account))
    }

    override suspend fun getId(firstName: String, lastName: String?, email: String): Int {
        return accountsDao.getId(firstName, lastName, email)
    }

    override suspend fun getUserByMetrics(firstName: String, email: String): AccountDto? {
        return assertNull(accountsDao.getUserByMetrics(firstName, email))
    }

    override suspend fun getUserById(id: Int): AccountDto? {
        return assertNull(accountsDao.getUserById(id))
    }

    override suspend fun saveCurrentUserId(id: Int) {
        sharedPreferences.saveCurrentUserId(id)
    }

    override suspend fun getCurrentUserId(): Int? {
        return sharedPreferences.getCurrentUserId()
    }

    private fun assertNull(account: AccountDtoDao?): AccountDto? {
        return if (account != null) {
            accountsUtils.convertToAccountDto(account)
        } else {
            null
        }
    }
}