package com.raphel.spoc.domain.usecase

import com.raphel.spoc.domain.model.OrderDetailsDomainModel
import com.raphel.spoc.domain.repository.OrdersRepository
import javax.inject.Inject

class GetOrderDetails @Inject constructor(private val ordersRepository: OrdersRepository) {

    suspend fun execute(key: String,orderId: Int):OrderDetailsDomainModel {

        return ordersRepository.getOrderDetailsFromServer(key,orderId)
    }
}