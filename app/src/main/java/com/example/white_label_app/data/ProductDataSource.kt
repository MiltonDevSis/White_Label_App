package com.example.white_label_app.data

import android.net.Uri
import com.example.white_label_app.domain.model.Product

interface ProductDataSource {

    suspend fun getProduct(): List<Product>

    suspend fun uploadProductImage(image: Uri): String

    suspend fun createProduct(product: Product): Product
}