package com.raphel.spoc.domain.mapper

interface TwoInputMapper<F1,F2,T> {

    fun map(input1:F1,input2:F2):T

}