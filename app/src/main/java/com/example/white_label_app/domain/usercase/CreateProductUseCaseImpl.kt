package com.example.white_label_app.domain.usercase

import android.net.Uri
import com.example.white_label_app.data.ProductRepository
import com.example.white_label_app.domain.model.Product
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class CreateProductUseCaseImpl @Inject constructor(
    private val uploadProductImageUseCase: UploadProductImageUseCase,
    private val productRepository: ProductRepository
) : CreateProductUseCase {

    override suspend fun invoke(description: String, price: Double, image: Uri): Product {
        return try {
            val imageUrl = uploadProductImageUseCase(image)
            val product = Product(UUID.randomUUID().toString(), description, price, imageUrl)
            productRepository.createProduct(product)
        } catch (e: Exception) {
            throw e
        }
    }
}