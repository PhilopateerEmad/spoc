package com.raphel.spoc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raphel.spoc.domain.model.*
import com.raphel.spoc.domain.usecase.GetOrderDetails
//import com.raphel.spoc.domain.usecase.AddOrder
//import com.raphel.spoc.domain.usecase.AddProduct
import com.raphel.spoc.domain.usecase.GetOrdersList
import com.raphel.spoc.domain.usecase.LogIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
//    private val addOrder: AddOrder,
//    private val addProduct: AddProduct,
    private val logIn: LogIn,
    private val getOrdersList: GetOrdersList,
    private val getOrderDetails: GetOrderDetails,


    ):ViewModel() {

    init {

    }

//    fun addOrder(order: OrderDomainModel){
//        viewModelScope.launch {
//            addOrder.execute(order)
//        }
//
//    }
//
//    fun addProduct(product: ProductDetailsDomainModel){
//        viewModelScope.launch {
//            addProduct.execute(product)
//        }
//    }


    suspend fun logIn(login:LoginDomainModel):LoginResponseDomainModel{
            return logIn.execute(login)
    }

    suspend fun getOrdersList(key:String,managerId: Int):List<OrderDomainModel>{
        return getOrdersList.execute(key,managerId)
    }

    suspend fun getOrderDetails(key:String,orderId: Int):OrderDetailsDomainModel{
        return getOrderDetails.execute(key,orderId)
    }
}