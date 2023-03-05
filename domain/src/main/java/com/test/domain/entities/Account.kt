package com.test.domain.entities

interface Account {
    val id: Int
    val firstName: String
    val lastName: String?
    val password: String
    val photoUrl: String
    val email: String
    val balance: Int
}