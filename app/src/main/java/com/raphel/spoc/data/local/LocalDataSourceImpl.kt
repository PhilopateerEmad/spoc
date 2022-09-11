package com.raphel.spoc.data.local

import androidx.lifecycle.LiveData
import com.raphel.spoc.data.local.ordersdatabase.entites.OrderDataModel
import com.raphel.spoc.data.local.ordersdatabase.entites.OrdersDao
import com.raphel.spoc.data.local.ordersdatabase.entites.ProductDataModel


import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val ordersDao: OrdersDao): LocalDataSource {
    override suspend fun addOrder(order: OrderDataModel) {
        ordersDao.addOrder(order)
    }

    override suspend fun addOrders(ordersList: List<OrderDataModel>) {
        ordersDao.addOrders(ordersList)
    }

    override fun getOrders(): LiveData<List<OrderDataModel>> {
        return ordersDao.getOrders()
    }

    override fun getOrderById(vararg orderId: String): LiveData<OrderDataModel> {
        return ordersDao.getOrder(*orderId)
    }

    override suspend fun updateOrder(order: OrderDataModel) {
        ordersDao.addOrder(order)
    }

    override suspend fun deleteOrder(orderId: String): Int {
        return ordersDao.deleteOrder(orderId)
    }

    override suspend fun addProduct(product: ProductDataModel) {
        ordersDao.addProduct(product)
    }

    override suspend fun addProducts(productsList: List<ProductDataModel>) {
        ordersDao.addProducts(productsList)
    }

    override fun getProducts(): LiveData<List<ProductDataModel>> {
        return ordersDao.getProducts()
    }

    override fun getProductById(vararg productId: String): LiveData<ProductDataModel> {
        return ordersDao.getProduct(*productId)
    }

    override suspend fun updateProduct(product: ProductDataModel) {
        ordersDao.addProduct(product)
    }

    override suspend fun deleteProduct(productId: String): Int {
        return ordersDao.deleteProduct(productId)

    }
}
