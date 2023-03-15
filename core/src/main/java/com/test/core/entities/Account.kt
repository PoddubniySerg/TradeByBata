package com.test.core.entities

interface Account {
    val id: Int
    val firstName: String
    val lastName: String?
    val password: String
    val photoUrl: String
    val email: String
    val balance: Int
}