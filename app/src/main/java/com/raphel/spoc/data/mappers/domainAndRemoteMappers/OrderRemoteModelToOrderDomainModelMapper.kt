package com.raphel.spoc.data.mappers.domainAndRemoteMappers
import com.raphel.spoc.data.remote.models.OrderRemoteModel
import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.OrderDomainModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OrderRemoteModelToOrderDomainModelMapper : IMapper<OrderRemoteModel, OrderDomainModel> {
    override fun map(input: OrderRemoteModel): OrderDomainModel {
        return OrderDomainModel(
            input.id?.toString()?:"0",
            input.pharmacyName?:"",
            input.agentName?:"",
            bindDateType(input.orderDate),

        )
    }

    private fun bindDateType(orderDate: String?): String {
        val localDateTime: LocalDateTime = LocalDateTime.parse(orderDate ?: "0000-00-00T00:00:00")
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        return formatter.format(localDateTime)
    }



}