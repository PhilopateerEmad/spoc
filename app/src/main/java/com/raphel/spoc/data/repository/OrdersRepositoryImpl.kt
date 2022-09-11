package com.raphel.spoc.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.raphel.spoc.data.local.LocalDataSource
import com.raphel.spoc.data.local.ordersdatabase.entites.OrderDataModel
import com.raphel.spoc.data.local.ordersdatabase.entites.ProductDataModel
import com.raphel.spoc.data.remote.RemoteDataSource
import com.raphel.spoc.data.remote.models.LoginRemoteModel
import com.raphel.spoc.data.remote.models.LoginResponseRemoteModel
import com.raphel.spoc.data.remote.models.OrderDetailsRemoteModel
import com.raphel.spoc.data.remote.models.OrderRemoteModel
import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.*
import com.raphel.spoc.domain.repository.OrdersRepository
import javax.inject.Inject

class OrdersRepositoryImpl @Inject constructor (private val localDataSource: LocalDataSource,
                                                private val remoteDataSource: RemoteDataSource,
                                                //private val orderDataItemToOrderDomainItemMapper: IMapper<OrderDataModel, OrderDomainModel>,
                                                //private val orderDomainItemToOrderDataItemMapper: IMapper<OrderDomainModel, OrderDataModel>,
                                                //private val productDataItemToProductDomainItemMapper: IMapper<ProductDataModel, ProductDetailsDomainModel>,
                                                //private val productDomainItemToProductDataItemMapper: IMapper<ProductDetailsDomainModel, ProductDataModel>,
                                                //private val orderDataListToOrderDomainListMapper: IMapper<List<OrderDataModel>, List<OrderDomainModel>>,
                                                //private val orderDomainListToOrderDataListMapper: IMapper<List<OrderDomainModel>, List<OrderDataModel>>,
                                                //private val productDataListToProductDomainListMapper: IMapper<List<ProductDataModel>, List<ProductDetailsDomainModel>>,
                                                //private val productDomainListToProductDataListMapper: IMapper<List<ProductDetailsDomainModel>, List<ProductDataModel>>,
                                                private val loginDomainModelToLoginRemoteModelMapper: IMapper<LoginDomainModel, LoginRemoteModel>,
                                                private val loginResponseRemoteModelToLoginResponseDomainModelMapper: IMapper<LoginResponseRemoteModel, LoginResponseDomainModel>,
                                                private val orderDetailsRemoteModelOrderDomainModelMapper: IMapper<OrderDetailsRemoteModel, OrderDetailsDomainModel>,
                                                //private val orderRemoteModelToOrderDomainModelMapper : IMapper<OrderRemoteModel, OrderDomainModel>,
                                                private val ordersListRemoteModelToOrdersListDomainModelMapper : IMapper<List<OrderRemoteModel>, List<OrderDomainModel>>




                                                ): OrdersRepository {


//    override suspend fun deleteOrderFromDB(orderId: String) {
//        localDataSource.deleteOrder(orderId)
//    }



//    override suspend fun insertOrderToDB(orderDomainModel: OrderDomainModel) {
//        localDataSource.addOrder(orderDomainItemToOrderDataItemMapper.map(orderDomainModel))
//    }
//
//    override suspend fun insertOrdersToDB(ordersList: List<OrderDomainModel>) {
//        localDataSource.addOrders(orderDomainListToOrderDataListMapper.map(ordersList))
//    }
//
//    override suspend fun insertProductToDB(productDetailsDomainModel: ProductDetailsDomainModel) {
//        localDataSource.addProduct(productDomainItemToProductDataItemMapper.map(productDetailsDomainModel))
//    }
//
//    override suspend fun insertProductsToDB(productsList: List<ProductDetailsDomainModel>) {
//        localDataSource.addProducts(productDomainListToProductDataListMapper.map(productsList))
//    }
//
//    override fun getOrdersFromDB(): LiveData<List<OrderDomainModel>> {
//        return Transformations.map(localDataSource.getOrders()) { OrderDataList ->
//            orderDataListToOrderDomainListMapper.map(OrderDataList)
//        }
//    }
//
//    override fun getOrderFromDB(orderId:String): LiveData<OrderDomainModel> {
//        return Transformations.map(localDataSource.getOrderById(orderId)){
//            orderDataItemToOrderDomainItemMapper.map(it)
//        }
//    }

    override suspend fun login(login: LoginDomainModel): LoginResponseDomainModel {
        val loginResponseRemoteModel = remoteDataSource.login(loginDomainModelToLoginRemoteModelMapper.map(login))
        return loginResponseRemoteModelToLoginResponseDomainModelMapper.map(loginResponseRemoteModel)
    }

    override suspend fun getOrdersListFromServer(key: String,managerId: Int): List<OrderDomainModel> {
        val ordersList = remoteDataSource.getOrders(key,managerId)
        return ordersListRemoteModelToOrdersListDomainModelMapper.map(ordersList)
    }

    override suspend fun getOrderDetailsFromServer(key:String,orderId: Int): OrderDetailsDomainModel {
        val orderDetails = remoteDataSource.getOrderById(key,orderId)
        return orderDetailsRemoteModelOrderDomainModelMapper.map(orderDetails)

    }

    override suspend fun refreshCache() {

    }



}
