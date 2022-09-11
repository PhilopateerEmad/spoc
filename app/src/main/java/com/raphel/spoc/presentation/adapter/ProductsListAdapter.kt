package com.raphel.spoc.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.compose.runtime.structuralEqualityPolicy

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raphel.spoc.databinding.ProductsCardViewBinding
import com.raphel.spoc.domain.model.ProductDetailsDomainModel
import com.raphel.spoc.util.ProductDiffUtil


class ProductsListAdapter() : ListAdapter<ProductDetailsDomainModel, ProductsListAdapter.ViewHolder>(
    ProductDiffUtil()
) {

    class ViewHolder(val binding: ProductsCardViewBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductsCardViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            productName.text = getItem(holder.adapterPosition).name
            println(getItem(holder.adapterPosition).name)
            quantity.text = "x"+getItem(holder.adapterPosition).quantity.toString()
            expiryDate.text = getItem(holder.adapterPosition).expiryDate

            
        }
    }

}