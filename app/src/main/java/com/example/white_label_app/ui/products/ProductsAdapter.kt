package com.example.white_label_app.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.white_label_app.databinding.ItemProductBinding
import com.example.white_label_app.domain.model.Product
import com.example.white_label_app.util.toCurrency

class ProductsAdapter: ListAdapter<Product, ProductsAdapter.ProductsViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductsViewHolder(
        private val itemBind: ItemProductBinding
    ) : RecyclerView.ViewHolder(itemBind.root) {

        fun bind(product: Product) {
            itemBind.run {
                Glide.with(itemView)
                    .load(product.imageUrl)
                    .fitCenter()
                    .into(imageProduct)

                textDescription.text = product.description
                textPrice.text = product.price.toCurrency()
            }
        }

        companion object {

            fun create(parent: ViewGroup): ProductsViewHolder {
                val itemBind = ItemProductBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return ProductsViewHolder(itemBind)
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

        }
    }
}