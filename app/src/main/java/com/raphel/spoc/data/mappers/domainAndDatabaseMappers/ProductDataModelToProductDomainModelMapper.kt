package com.raphel.spoc.data.mappers.domainAndDatabaseMappers
import com.raphel.spoc.data.local.ordersdatabase.entites.ProductDataModel


import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.ProductDetailsDomainModel



//class ProductDataModelToProductDomainModelMapper : IMapper<ProductDataModel, ProductDetailsDomainModel> {
//    override fun map(input: ProductDataModel): ProductDetailsDomainModel {
//        return ProductDetailsDomainModel(
//            input.orderId,
//            input.name,
//            input.quantity
//        )
//    }
//}