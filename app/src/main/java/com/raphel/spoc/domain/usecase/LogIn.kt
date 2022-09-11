package com.raphel.spoc.domain.usecase

import com.raphel.spoc.domain.model.LoginDomainModel
import com.raphel.spoc.domain.model.LoginResponseDomainModel
import com.raphel.spoc.domain.model.OrderDomainModel
import com.raphel.spoc.domain.repository.OrdersRepository
import javax.inject.Inject

class LogIn @Inject constructor(private val ordersRepository: OrdersRepository) {

    suspend fun execute(login:LoginDomainModel):LoginResponseDomainModel{

        return ordersRepository.login(login)
    }
}