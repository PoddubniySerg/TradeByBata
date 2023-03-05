package com.test.domain.model.requests

class SignInObject(
    val firstName: String,
    val lastName: String? = null,
    val email: String,
    val photoUri: String
)