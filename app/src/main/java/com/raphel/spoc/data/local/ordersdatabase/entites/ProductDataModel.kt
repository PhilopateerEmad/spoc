package com.raphel.spoc.data.local.ordersdatabase.entites

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = OrderDataModel::class,
        childColumns = ["orderId"],
        parentColumns = ["orderId"]
    )])
data class ProductDataModel(
    @PrimaryKey(autoGenerate = false)
    val productId: Long,
    val orderId: String,
    val name: String,
    val description:String,
    val quantity: String,
    val expiryDate: String,

)


