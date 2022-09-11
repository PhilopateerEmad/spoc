package com.raphel.spoc.data.mappers.domainAndDatabaseMappers
import com.raphel.spoc.data.local.ordersdatabase.entites.OrderDataModel
import com.raphel.spoc.data.local.ordersdatabase.entites.ProductDataModel


import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.mapper.TwoInputMapper
import com.raphel.spoc.domain.model.OrderDetailsDomainModel
import com.raphel.spoc.domain.model.ProductDetailsDomainModel


//class OrderDataModelToOrderDetailsDomainModelMapper : TwoInputMapper<OrderDataModel,List<ProductDataModel>, OrderDetailsDomainModel> {
//    override fun map(input1: OrderDataModel, input2: List<ProductDataModel>): OrderDetailsDomainModel {
//
//        return OrderDetailsDomainModel(
//            input1.agentName,
//            input1.branchName,
//            input1.distributorName,
//            input1.expiredGood,
//            input1.orderId,
//            input1.pharmacyName,
//            input1.orderDate,
//            input1.pharmacyNameAtDistributor,
//            bindProductsDataToDomainModel(input2),
//            input1.valueOfExpiredGood,
//        )
//
//    }
//
//    private fun bindProductsDataToDomainModel(input: List<ProductDataModel>): List<ProductDetailsDomainModel> {
//
//    }
//
//}