package com.raphel.spoc.domain.model

data class LoginResponseDomainModel(
    val isSuccess: Boolean,
    val message: String,
    val data: String,
)
