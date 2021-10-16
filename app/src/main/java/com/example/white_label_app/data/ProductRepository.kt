package com.example.white_label_app.data

import android.net.Uri
import com.example.white_label_app.domain.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val dataSource: ProductDataSource
) {

    suspend fun getProducts(): List<Product> = dataSource.getProducts()

    suspend fun uploadProductImage(imageUri: Uri): String =
        dataSource.uploadProductImage(imageUri)

    suspend fun createProduct(product: Product): Product =
        dataSource.createProduct(product)
}