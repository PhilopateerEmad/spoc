package com.raphel.spoc.data.mappers.domainAndRemoteMappers

import com.raphel.spoc.data.remote.models.OrderDetailsRemoteModel
import com.raphel.spoc.data.remote.models.ProductDetailsRemoteModel
import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.OrderDetailsDomainModel
import com.raphel.spoc.domain.model.ProductDetailsDomainModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class OrderDetailsRemoteModelOrderDomainModelMapper: IMapper<OrderDetailsRemoteModel, OrderDetailsDomainModel> {
    override fun map(input: OrderDetailsRemoteModel): OrderDetailsDomainModel {
        return OrderDetailsDomainModel(
            input.agentName?:"",
            input.branchName?:"",
            input.distributorName?:"",
            input.expiryGoods?:false,
            input.id?.toString()?:"",
            bindDateType(input.orderDate),
            input.pharmacyCode?:"",
            input.pharmacyName?:"",
            bindProductsRemoteToDomainModel(input.products,input.id),
            input.valueOfExpiredGoods?:"",
        )
    }

    private fun bindProductsRemoteToDomainModel(productDetailsRemoteModel: List<ProductDetailsRemoteModel>?,orderId:Int?): List<ProductDetailsDomainModel> {

        val productsList = mutableListOf<ProductDetailsDomainModel>()

        if (productDetailsRemoteModel != null) {
            for (product in productDetailsRemoteModel.indices) {

                productsList.add(
                    product, ProductDetailsDomainModel(

                        productDetailsRemoteModel[product].description ?: "",
                        bindDateType(productDetailsRemoteModel[product].expirydate),
                        productDetailsRemoteModel[product].id ?.toString()?:"",
                        orderId?.toString()?:"",
                        productDetailsRemoteModel[product].name ?: "",
                        productDetailsRemoteModel[product].quantity ?: 0,
                    )
                )
            }
        }
        else{
            productsList.add(0,ProductDetailsDomainModel(
                "",
                "",
                "",
                "",
                "",
                0,
            ))
        }

        return productsList
    }

    private fun bindDateType(orderDate: String?): String {

        val localDateTime: LocalDateTime = LocalDateTime.parse(orderDate?: "0000-00-00T00:00:00")
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        return formatter.format(localDateTime)
    }


}