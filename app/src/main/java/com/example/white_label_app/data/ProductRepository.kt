package com.example.white_label_app.data

import android.net.Uri
import com.example.white_label_app.domain.model.Product

class ProductRepository(private val dataSource: ProductDataSource) {

    suspend fun getProduct(): List<Product> = dataSource.getProduct()

    suspend fun uploadProductImage(image: Uri): String = dataSource.uploadProductImage(image)

    suspend fun createProduct(product: Product): Product = dataSource.createProduct(product)
}