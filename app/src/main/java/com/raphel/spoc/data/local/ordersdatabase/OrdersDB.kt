package com.raphel.spoc.data.local.ordersdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raphel.spoc.data.local.ordersdatabase.entites.OrderDataModel
import com.raphel.spoc.data.local.ordersdatabase.entites.OrdersDao
import com.raphel.spoc.data.local.ordersdatabase.entites.ProductDataModel

@Database(entities = [OrderDataModel::class, ProductDataModel::class], version = 1, exportSchema = false)
abstract class OrdersDB : RoomDatabase(){
    abstract fun ordersDao(): OrdersDao

}