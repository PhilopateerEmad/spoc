package com.raphel.spoc.data.remote

import com.raphel.spoc.data.remote.models.*
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val spocApi: SpocApi): RemoteDataSource {
    override suspend fun login(login: LoginRemoteModel): LoginResponseRemoteModel {
        val response = spocApi.login(login)

        return if(response.isSuccessful) {
            response.body() ?: LoginResponseRemoteModel(false,"no message",0)

        }
        else{
            println("The error is ${response.errorBody().toString()}")
            LoginResponseRemoteModel(false,"no message",0)
        }
    }

    override suspend fun getOrders(key:String,id: Int): List<OrderRemoteModel> {
        val response = spocApi.getOrders(key,id)
        return if(response.isSuccessful) {
            response.body() ?: OrdersListRemoteModel()

        }
        else{
            println("The error is ${response.errorBody().toString()}")
            OrdersListRemoteModel()
        }
    }

    override suspend fun getOrderById(key: String, id: Int): OrderDetailsRemoteModel {
        val response = spocApi.getOrderDetailsById(key,id)
        println(response.body()?.products)
        return if(response.isSuccessful) {

            response.body() ?: OrderDetailsRemoteModel(

                0,
                "",
                "",
                "",
                "",
                "",
                "",
                false,
                "",
                listOf(ProductDetailsRemoteModel(0,"","",0,"")),
            )

        }
        else{
            println("The error is ${response.errorBody().toString()}")
            OrderDetailsRemoteModel(
                0,
                "",
                "",
                "",
                "",
                "",
                "",
                false,
                "",
                listOf(ProductDetailsRemoteModel(0,"","",0,"")),
            )
        }
    }

//    private fun bindProductsList(productDetailsRemoteModels: List<ProductDetailsRemoteModel>?): List<ProductDetailsRemoteModel>? {
//
//    }


}