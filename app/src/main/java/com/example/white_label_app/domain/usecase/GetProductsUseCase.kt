package com.example.white_label_app.domain.usecase

import com.example.white_label_app.domain.model.Product


interface GetProductsUseCase {

    suspend operator fun invoke(): List<Product>
}