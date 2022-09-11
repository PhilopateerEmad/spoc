package com.raphel.spoc.data.mappers.domainAndDatabaseMappers

import com.raphel.spoc.data.local.ordersdatabase.entites.ProductDataModel
import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.ProductDetailsDomainModel
import javax.inject.Inject

//class ProductListDomainModelToProductListDataModelMapper @Inject constructor(private val mapper: IMapper<ProductDetailsDomainModel, ProductDataModel>) :
//    IMapper<List<ProductDetailsDomainModel>, List<ProductDataModel>> {
//
//    override fun map(input: List<ProductDetailsDomainModel>): List<ProductDataModel> {
//        return input.map {
//            mapper.map(it)
//        }
//    }
//}