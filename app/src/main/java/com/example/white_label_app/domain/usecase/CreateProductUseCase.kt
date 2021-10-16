package com.example.white_label_app.domain.usecase

import android.net.Uri
import com.example.white_label_app.domain.model.Product

interface CreateProductUseCase {

    suspend operator fun invoke(description: String, price: Double, imageUri: Uri): Product
}