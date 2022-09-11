package com.raphel.spoc.data.remote

import com.raphel.spoc.data.remote.models.*
import retrofit2.Response
import retrofit2.http.*

interface SpocApi {

    @POST("api/Admin/LoginManager")
    suspend fun login(@Body login:LoginRemoteModel): Response<LoginResponseRemoteModel>

    @GET("api/Order/GetOrdersManager")
    suspend fun getOrders(@Header("ApiKey")key:String,@Query("ManagerId")id:Int): Response<List<OrderRemoteModel>>

    @GET("api/Order/GetOrder")
    suspend fun getOrderDetailsById(@Header("ApiKey")key:String,@Query("orderId")id:Int): Response<OrderDetailsRemoteModel>


}
