package com.raphel.spoc.domain.model

data class ProductDetailsDomainModel(
    val description: String,
    val expiryDate: String,
    val productId: String,
    val orderId: String,
    val name: String,
    val quantity: Int,
)


