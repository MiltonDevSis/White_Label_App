package com.example.white_label_app.domain.usercase

import android.net.Uri
import com.example.white_label_app.data.ProductRepository

class UploadProductImageUseCaseImpl(
    private val productRepository: ProductRepository
) : UploadProductImageUseCase {

    override suspend fun invoke(image: Uri): String {
        return productRepository.uploadProductImage(image)
    }
}