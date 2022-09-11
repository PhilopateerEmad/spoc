package com.raphel.spoc.data.mappers.domainAndDatabaseMappers
import com.raphel.spoc.data.local.ordersdatabase.entites.ProductDataModel


import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.ProductDetailsDomainModel
import javax.inject.Inject


//class ProductListDataModelToProductListDomainModelMapper @Inject constructor(private val mapper: IMapper<ProductDataModel, ProductDetailsDomainModel>) :
//    IMapper<List<ProductDataModel>, List<ProductDetailsDomainModel>> {
//
//    override fun map(input: List<ProductDataModel>): List<ProductDetailsDomainModel> {
//        return input.map {
//            mapper.map(it)
//        }
//    }
//}