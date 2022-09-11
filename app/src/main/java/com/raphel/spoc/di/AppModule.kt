package com.raphel.spoc.di

import android.content.Context
import androidx.room.Room
import com.raphel.spoc.data.local.LocalDataSource
import com.raphel.spoc.data.local.LocalDataSourceImpl
import com.raphel.spoc.data.local.ordersdatabase.OrdersDB

import com.raphel.spoc.data.local.ordersdatabase.entites.OrdersDao

import com.raphel.spoc.data.mappers.domainAndRemoteMappers.*
import com.raphel.spoc.data.remote.RemoteDataSource
import com.raphel.spoc.data.remote.RemoteDataSourceImpl
import com.raphel.spoc.data.remote.SpocApi
import com.raphel.spoc.data.remote.models.LoginRemoteModel
import com.raphel.spoc.data.remote.models.LoginResponseRemoteModel
import com.raphel.spoc.data.remote.models.OrderDetailsRemoteModel
import com.raphel.spoc.data.remote.models.OrderRemoteModel
import com.raphel.spoc.data.repository.OrdersRepositoryImpl
import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.*
import com.raphel.spoc.domain.repository.OrdersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOrdersDatabase(@ApplicationContext context: Context): OrdersDB {
        return Room.databaseBuilder(
            context,
            OrdersDB::class.java,
            "OrdersDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideSpocApi(): SpocApi {
        return Retrofit.Builder()
            .baseUrl("http://fadygeorge-001-site1.htempurl.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpocApi::class.java)
    }


    @Provides
    @Singleton
    fun provideOrdersDao(ordersDB: OrdersDB): OrdersDao {
        return ordersDB.ordersDao()
    }


    @Provides
    @Singleton
    fun provideLocalSource(ordersDao: OrdersDao): LocalDataSource {
        return LocalDataSourceImpl(ordersDao)
    }

    @Provides
    @Singleton
    fun provideRemoteSource(spocApi: SpocApi): RemoteDataSource {
        return RemoteDataSourceImpl(spocApi)
    }

    @Provides
    @Singleton
    fun provideRepository(localDataSource: LocalDataSource,
                          remoteDataSource: RemoteDataSource,
                          //orderDataItemToOrderDomainItemMapper: IMapper<OrderDataModel, OrderDomainModel>,
                          //orderDomainItemToOrderDataItemMapper: IMapper<OrderDomainModel, OrderDataModel>,
                          //productDataItemToProductDomainItemMapper: IMapper<ProductDataModel, ProductDetailsDomainModel>,
                          //productDomainItemToProductDataItemMapper: IMapper<ProductDetailsDomainModel, ProductDataModel>,
                          //orderDataListToOrderDomainListMapper: IMapper<List<OrderDataModel>, List<OrderDomainModel>>,
                          //orderDomainListToOrderDataListMapper: IMapper<List<OrderDomainModel>, List<OrderDataModel>>,
                          //productDataListToProductDomainListMapper: IMapper<List<ProductDataModel>, List<ProductDetailsDomainModel>>,
                          //productDomainListToProductDataListMapper: IMapper<List<ProductDetailsDomainModel>, List<ProductDataModel>>,
                          loginDomainModelToLoginRemoteModelMapper: IMapper<LoginDomainModel, LoginRemoteModel>,
                          loginResponseRemoteModelToLoginResponseDomainModelMapper: IMapper<LoginResponseRemoteModel, LoginResponseDomainModel>,
                          orderDetailsRemoteModelOrderDomainModelMapper: IMapper<OrderDetailsRemoteModel, OrderDetailsDomainModel>,
                          orderRemoteModelToOrderDomainModelMapper : IMapper<OrderRemoteModel, OrderDomainModel>,
                          ordersListRemoteModelToOrdersListDomainModelMapper : IMapper<List<OrderRemoteModel>, List<OrderDomainModel>>

    ): OrdersRepository {
        return OrdersRepositoryImpl(
            localDataSource,
            remoteDataSource,
            //orderDataItemToOrderDomainItemMapper,
            //orderDomainItemToOrderDataItemMapper,
            //productDataItemToProductDomainItemMapper,
            //productDomainItemToProductDataItemMapper,
            //orderDataListToOrderDomainListMapper,
            //orderDomainListToOrderDataListMapper,
            //productDataListToProductDomainListMapper,
            //productDomainListToProductDataListMapper,
            loginDomainModelToLoginRemoteModelMapper,
            loginResponseRemoteModelToLoginResponseDomainModelMapper,
            orderDetailsRemoteModelOrderDomainModelMapper,
            //orderRemoteModelToOrderDomainModelMapper,
            ordersListRemoteModelToOrdersListDomainModelMapper,


        )

    }

//    @Provides
//    @Singleton
//    fun provideOrderDataModelToOrderDomainModelMapper(): IMapper<OrderDataModel, OrderDomainModel>
//    {
//        return OrderDataModelToOrderDomainModelMapper()
//    }

//    @Provides
//    @Singleton
//    fun provideOrderDomainModelToOrderDataModelMapper(): IMapper<OrderDomainModel, OrderDataModel>
//    {
//        return OrderDomainModelToOrderDataModelMapper()
//    }


//    @Provides
//    @Singleton
//    fun provideOrderDataListToOrderDomainListMapper(mapper: IMapper<OrderDataModel, OrderDomainModel>): IMapper<List<OrderDataModel>, List<OrderDomainModel>>
//    {
//        return OrderListDataModelToOrderListDomainModelMapper(mapper)
//    }

//    @Provides
//    @Singleton
//    fun provideOrderDomainListToOrderDataListMapper(mapper: IMapper<OrderDomainModel, OrderDataModel>): IMapper<List<OrderDomainModel>, List<OrderDataModel>>
//    {
//        return OrderListDomainModelToOrderListDataModelMapper(mapper)
//    }

    @Provides
    @Singleton
    fun provideOrderDetailsRemoteModelOrderDomainModelMapper(): IMapper<OrderDetailsRemoteModel, OrderDetailsDomainModel>
    {
        return OrderDetailsRemoteModelOrderDomainModelMapper()
    }

    @Provides
    @Singleton
    fun provideOrderRemoteModelToOrderDomainModelMapper():IMapper<OrderRemoteModel, OrderDomainModel>
    {
        return OrderRemoteModelToOrderDomainModelMapper()
    }


    @Provides
    @Singleton
    fun provideOrdersListRemoteModelToOrdersListDomainModelMapper(mapper: IMapper<OrderRemoteModel, OrderDomainModel>): IMapper<List<OrderRemoteModel>, List<OrderDomainModel>>
    {
        return OrdersListRemoteModelToOrdersListDomainModelMapper(mapper)
    }


    /***********************************/

//    @Provides
//    @Singleton
//    fun provideProductDataModelToProductDomainModelMapper(): IMapper<ProductDataModel, ProductDetailsDomainModel>
//    {
//        return ProductDataModelToProductDomainModelMapper()
//    }

//    @Provides
//    @Singleton
//    fun provideProductDomainModelToProductDataModelMapper(): IMapper<ProductDetailsDomainModel, ProductDataModel>
//    {
//        return ProductDomainModelToProductDataModelMapper()
//    }


//    @Provides
//    @Singleton
//    fun provideProductDataListToProductDomainListMapper(mapper: IMapper<ProductDataModel, ProductDetailsDomainModel>): IMapper<List<ProductDataModel>, List<ProductDetailsDomainModel>>
//    {
//        return ProductListDataModelToProductListDomainModelMapper(mapper)
//    }

//    @Provides
//    @Singleton
//    fun provideProductDomainListToProductDataListMapper(mapper: IMapper<ProductDetailsDomainModel, ProductDataModel>): IMapper<List<ProductDetailsDomainModel>, List<ProductDataModel>>
//    {
//        return ProductListDomainModelToProductListDataModelMapper(mapper)
//    }


    @Provides
    @Singleton
    fun provideLoginDomainModelToLoginRemoteModelMapper(): IMapper<LoginDomainModel, LoginRemoteModel>{
        return LoginDomainModelToLoginRemoteModelMapper()
    }

    @Provides
    @Singleton
    fun provideLoginResponseRemoteModelToLoginResponseDomainModelMapper(): IMapper<LoginResponseRemoteModel, LoginResponseDomainModel>{
        return LoginResponseRemoteModelToLoginResponseDomainModelMapper()
    }







}