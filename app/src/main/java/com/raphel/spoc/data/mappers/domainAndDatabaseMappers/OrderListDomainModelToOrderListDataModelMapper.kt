package com.raphel.spoc.data.mappers.domainAndDatabaseMappers

import com.raphel.spoc.data.local.ordersdatabase.entites.OrderDataModel
import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.OrderDomainModel
import javax.inject.Inject

//class OrderListDomainModelToOrderListDataModelMapper @Inject constructor(private val mapper: IMapper<OrderDomainModel, OrderDataModel>) :
//    IMapper<List<OrderDomainModel>, List<OrderDataModel>> {
//
//    override fun map(input: List<OrderDomainModel>): List<OrderDataModel> {
//        return input.map {
//            mapper.map(it)
//        }
//    }
//}