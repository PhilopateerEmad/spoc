package com.raphel.spoc.domain.model


data class OrderDomainModel(
    val orderId: String,
    val pharmacyName: String,
    val agentName: String,
    val orderDate: String,
)