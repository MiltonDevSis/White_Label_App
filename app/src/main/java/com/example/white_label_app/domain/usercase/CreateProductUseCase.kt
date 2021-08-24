package com.example.white_label_app.domain.usercase

import android.net.Uri

interface CreateProductUseCase {

    suspend operator fun invoke(description: String, price: Double, image: Uri) : String
}