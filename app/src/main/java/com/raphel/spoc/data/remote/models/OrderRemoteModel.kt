package com.raphel.spoc.data.remote.models

import com.google.gson.annotations.SerializedName

data class OrderRemoteModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("pharmacyName")
    val pharmacyName: String?,
    @SerializedName("orderDate")
    val orderDate: String?,
    @SerializedName("agentName")
    val agentName: String?,
)
