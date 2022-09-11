package com.raphel.spoc.data.local.ordersdatabase.entites

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class OrderDataModel (
    @PrimaryKey(autoGenerate = false)
    val orderId: String,
    val pharmacyName: String,
    val agentName: String,
    val orderDate: String,
    val distributorName: String,
    val branchName: String,
    val pharmacyNameAtDistributor: String,
    val expiredGood: Boolean,
    val valueOfExpiredGood: String,
    )