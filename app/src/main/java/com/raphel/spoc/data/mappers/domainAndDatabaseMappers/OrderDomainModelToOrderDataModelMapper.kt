package com.raphel.spoc.data.mappers.domainAndDatabaseMappers
import com.raphel.spoc.data.local.ordersdatabase.entites.OrderDataModel
import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.OrderDetailsDomainModel
import com.raphel.spoc.domain.model.OrderDomainModel

//class OrderDomainModelToOrderDataModelMapper : IMapper<OrderDetailsDomainModel, OrderDataModel> {
//    override fun map(input: OrderDetailsDomainModel): OrderDataModel {
//        return OrderDataModel(
//            input.orderId,
//            input.pharmacyName,
//            input.agentName,
//            input.orderDate,
//            input.distributorName,
//            input.branchName,
//            input.pharmacyCode,
//            input.expiryGoods,
//            input.valueOfExpiredGoods,
//        )
//    }
//}