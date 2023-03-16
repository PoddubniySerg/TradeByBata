package com.test.feature_page1.util

import com.test.core.entities.GoodDetails
import com.test.feature_page1.presentation.model.GoodDetailsDto
import javax.inject.Inject

class GoodDetailsUtil @Inject constructor() {

    fun convertGoodDetailsToDto(goodDetails: GoodDetails): GoodDetailsDto {
        return GoodDetailsDto(
            goodDetails.name,
            goodDetails.description,
            goodDetails.rating,
            goodDetails.reviewsCount,
            goodDetails.price,
            goodDetails.colors,
            goodDetails.imageUrls
        )
    }
}