package com.raphel.spoc.domain.repository

import androidx.lifecycle.LiveData
import com.raphel.spoc.domain.model.*


interface OrdersRepository {

//    suspend fun insertOrderToDB(orderDomainModel: OrderDomainModel)
//    suspend fun insertOrdersToDB(ordersList:List<OrderDomainModel>)
//    suspend fun insertProductToDB(productDetailsDomainModel: ProductDetailsDomainModel)
//    suspend fun insertProductsToDB(productsList: List<ProductDetailsDomainModel>)
//    fun getOrdersFromDB(): LiveData<List<OrderDomainModel>>
//    fun getOrderFromDB(orderId:String):LiveData<OrderDomainModel>
//    suspend fun deleteOrderFromDB(orderId: String)
    suspend fun login(login: LoginDomainModel):LoginResponseDomainModel
    suspend fun getOrdersListFromServer(key:String,managerId:Int):List<OrderDomainModel>
    suspend fun getOrderDetailsFromServer(key:String,orderId:Int):OrderDetailsDomainModel
    suspend fun refreshCache()


}