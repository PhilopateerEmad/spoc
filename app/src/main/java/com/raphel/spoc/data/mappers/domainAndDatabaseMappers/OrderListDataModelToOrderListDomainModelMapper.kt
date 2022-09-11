package com.raphel.spoc.data.mappers.domainAndDatabaseMappers

import com.raphel.spoc.data.local.ordersdatabase.entites.OrderDataModel
import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.OrderDomainModel
import javax.inject.Inject


//class OrderListDataModelToOrderListDomainModelMapper @Inject constructor(private val mapper: IMapper<OrderDataModel, OrderDomainModel>) :
//    IMapper<List<OrderDataModel>, List<OrderDomainModel>> {
//
//    override fun map(input: List<OrderDataModel>): List<OrderDomainModel> {
//        return input.map {
//            mapper.map(it)
//        }
//    }
//}