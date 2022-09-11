package com.raphel.spoc.util

import androidx.recyclerview.widget.DiffUtil

import com.raphel.spoc.domain.model.ProductDetailsDomainModel


class ProductDiffUtil: DiffUtil.ItemCallback<ProductDetailsDomainModel>()
{
    override fun areItemsTheSame(oldItem: ProductDetailsDomainModel, newItem: ProductDetailsDomainModel): Boolean {
        return oldItem.productId ==newItem.productId
    }

    override fun areContentsTheSame(oldItem: ProductDetailsDomainModel, newItem: ProductDetailsDomainModel): Boolean {
        return oldItem == newItem
    }
}