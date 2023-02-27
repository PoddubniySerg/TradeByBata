package com.test.domain.repository

import com.test.domain.entity.Account
import com.test.domain.model.responses.UserMetricsResponse

interface GoogleRepository {

    fun authorise(): UserMetricsResponse?
}