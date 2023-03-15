package com.test.data.store.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.domain.entities.Account

@Entity(tableName = "accounts")
class AccountDtoDao(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Int,

    @ColumnInfo(name = "first_name", index = true)
    override val firstName: String,

    @ColumnInfo(name = "last_name")
    override val lastName: String?,

    @ColumnInfo(name = "password")
    override val password: String,

    @ColumnInfo(name = "photo_url")
    override val photoUrl: String,

    @ColumnInfo(name = "email", index = true)
    override val email: String,

    @ColumnInfo(name = "balance")
    override val balance: Int
) : com.test.core.entities.Account, Account