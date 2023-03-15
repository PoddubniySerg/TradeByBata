package com.test.feature_page1.util

import com.test.core.entities.Brand

class BrandsUtil {

    fun create(posterUrl: String): Brand {
        return object : Brand {
            override val posterUrl: String
                get() = posterUrl

        }
    }
}