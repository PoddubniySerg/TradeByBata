package com.test.domain.repository

import com.test.domain.model.responses.UserMetricsResponse

interface AppleRepository {

    fun authorise(): UserMetricsResponse?
}