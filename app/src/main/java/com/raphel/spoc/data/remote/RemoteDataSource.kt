package com.raphel.spoc.data.remote
import com.raphel.spoc.data.remote.models.LoginRemoteModel
import com.raphel.spoc.data.remote.models.LoginResponseRemoteModel
import com.raphel.spoc.data.remote.models.OrderDetailsRemoteModel
import com.raphel.spoc.data.remote.models.OrderRemoteModel



interface RemoteDataSource {
    suspend fun login(login: LoginRemoteModel):LoginResponseRemoteModel
    suspend fun getOrders(key:String,id:Int): List<OrderRemoteModel>
    suspend fun getOrderById(key:String,id:Int): OrderDetailsRemoteModel
}