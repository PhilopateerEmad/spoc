package com.raphel.spoc.data.local

import androidx.lifecycle.LiveData
import com.raphel.spoc.data.local.ordersdatabase.entites.OrderDataModel
import com.raphel.spoc.data.local.ordersdatabase.entites.ProductDataModel


interface LocalDataSource {

    /*pushing orders*/
    suspend fun addOrder(order: OrderDataModel)
    suspend fun addOrders(ordersList:List<OrderDataModel>)
    /*getting orders*/

    fun getOrders():LiveData<List<OrderDataModel>>
    fun getOrderById(vararg orderId: String):LiveData<OrderDataModel>

    /*updating orders*/

    suspend fun updateOrder(order: OrderDataModel)
    /*deleting orders*/

    suspend fun deleteOrder(orderId: String):Int


    /*pushing products*/
    suspend fun addProduct(product: ProductDataModel)
    suspend fun addProducts(productsList:List<ProductDataModel>)
    /*getting products*/

    fun getProducts():LiveData<List<ProductDataModel>>
    fun getProductById(vararg productId: String):LiveData<ProductDataModel>

    /*updating products*/

    suspend fun updateProduct(product: ProductDataModel)
    /*deleting products*/

    suspend fun deleteProduct(productId: String):Int




}