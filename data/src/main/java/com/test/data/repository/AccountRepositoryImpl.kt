package com.test.data.repository

import com.test.data.DataApp
import com.test.data.store.db.AccountsDao
import com.test.data.store.db.dto.AccountDtoDao
import com.test.data.store.files.FilesRepository
import com.test.data.store.sharedpreferences.SharedPreferences
import com.test.domain.entities.Account
import com.test.domain.model.params.*
import com.test.domain.repository.AccountRepository

open class AccountRepositoryImpl : AccountRepository {

    private val sharedPreferences: SharedPreferences = SharedPreferences()

    private val accountsDao: AccountsDao = DataApp.getDataBase().accountDao()

    private val fileStorage: FilesRepository = FilesRepository()

    override suspend fun getCurrentUserId(): Int? {
        return sharedPreferences.getCurrentUserId()
    }

    override suspend fun saveUserId(param: SaveUserIdParams) {
        sharedPreferences.saveCurrentUserId(param.id)
    }

    override suspend fun removeUserId() {
        sharedPreferences.removeCurrentUserId()
    }

    override suspend fun getUserById(param: GetUserByIdParams): Account? {
        return accountsDao.getUserById(param.id)
    }

    override suspend fun getUserByMetrics(param: UserMetricParams): Account? {
        return accountsDao.getUserByMetrics(param.firstName, param.email)
    }

    override suspend fun create(param: CreateAccountParams): Account? {
        accountsDao.save(
            AccountDtoDao(
                id = 0,
                firstName = param.firstName,
                lastName = param.lastName,
                password = "",
                photoUrl = param.photoUri,
                email = param.email,
                balance = 0
            )
        )
        val id = accountsDao.getId(param.firstName, param.lastName, param.email)
        val account =
            AccountDtoDao(
                id = id,
                firstName = param.firstName,
                lastName = param.lastName,
                password = "",
                photoUrl = "${param.photoUri}/$id",
                email = param.email,
                balance = 0
            )
        accountsDao.update(account)
        return account
    }

    override suspend fun login(param: LoginParams): Account? {
        return accountsDao.login(param.login, param.password)
    }

    override suspend fun remove(param: RemoveAccountParams) {
        fileStorage.remove(accountsDao.getPhotoUri(param.id))
        accountsDao.remove(param.id)
    }

    override suspend fun savePhoto(param: SavePhotoParams) {
        fileStorage.save(param.uri, param.content)
    }
}