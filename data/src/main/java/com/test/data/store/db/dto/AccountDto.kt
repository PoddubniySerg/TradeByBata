package com.test.data.store.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.domain.entity.Account

@Entity(tableName = "accounts")
class AccountDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Int = 0,

    @ColumnInfo(name = "first_name", index = true)
    override val firstName: String,

    @ColumnInfo(name = "last_name")
    override val lastName: String?,

    @ColumnInfo(name = "password")
    override val password: String,

    @ColumnInfo(name = "photo_url")
    override val photoUrl: String?,

    @ColumnInfo(name = "email")
    override val email: String,

    @ColumnInfo(name = "balance")
    override val balance: Int
) : Account