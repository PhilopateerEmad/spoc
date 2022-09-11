package com.raphel.spoc.data.local.ordersdatabase.entites
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(order: OrderDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrders(ordersList:List<OrderDataModel>)

    @Query("select * from OrderDataModel where orderId = :orderId")
    fun getOrder(vararg orderId: String): LiveData<OrderDataModel>

    @Query("select * from OrderDataModel")
    fun getOrders(): LiveData<List<OrderDataModel>>

    @Query("SELECT EXISTS(SELECT * FROM OrderDataModel WHERE orderId = :orderId)")
    suspend fun isOrderExists(orderId : Int) : Boolean

    @Query("delete from OrderDataModel where orderId = :orderId")
    suspend fun deleteOrder(orderId: String): Int


    /**/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: ProductDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProducts(productsList:List<ProductDataModel>)

    @Query("select * from ProductDataModel where productId = :productId")
    fun getProduct(vararg productId: String): LiveData<ProductDataModel>

    @Query("select * from ProductDataModel")
    fun getProducts(): LiveData<List<ProductDataModel>>

    @Query("SELECT EXISTS(SELECT * FROM ProductDataModel WHERE productId = :productId)")
    suspend fun isProductExists(productId : Int) : Boolean

    @Query("delete from ProductDataModel where productId = :productId")
    suspend fun deleteProduct(productId: String): Int

}