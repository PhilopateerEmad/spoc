package com.raphel.spoc.data.mappers.domainAndRemoteMappers

import com.raphel.spoc.data.remote.models.OrderRemoteModel
import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.OrderDomainModel
import javax.inject.Inject

class OrdersListRemoteModelToOrdersListDomainModelMapper @Inject constructor(private val mapper: IMapper<OrderRemoteModel, OrderDomainModel>) :
    IMapper<List<OrderRemoteModel>, List<OrderDomainModel>> {

    override fun map(input: List<OrderRemoteModel>): List<OrderDomainModel> {
        return input.map {
            mapper.map(it)
        }
    }
}
