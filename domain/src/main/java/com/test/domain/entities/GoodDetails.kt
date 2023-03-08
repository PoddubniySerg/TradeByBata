package com.test.domain.entities

interface GoodDetails {

    val name: String
    val description: String
    val rating: Double
    val reviewsCount: Int
    val price: Double
    val colors: List<String>
    val imageUrls: List<String>
}