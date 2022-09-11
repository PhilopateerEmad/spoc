package com.raphel.spoc.domain.mapper

interface IMapper<F,T> {

    fun map(input:F):T

}