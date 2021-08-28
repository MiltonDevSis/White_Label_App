package com.example.white_label_app.domain.usercase

import com.example.white_label_app.data.ProductRepository
import com.example.white_label_app.domain.model.Product

class GetProductUseCaseImpl(
    private val productRepository: ProductRepository
): GetProductsUseCase {

    override suspend fun invoke(): List<Product> {
        return productRepository.getProduct()
    }
}