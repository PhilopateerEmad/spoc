package com.raphel.spoc.data.remote.models

data class LoginResponseRemoteModel(
    val isSuccess: Boolean?,
    val token: String?,
    val data: Int?,
)

