package com.example.white_label_app.domain.usercase

import android.net.Uri
import com.example.white_label_app.domain.model.Product

interface CreateProductUseCase {

    suspend operator fun invoke(description: String, price: Double, image: Uri) : Product
}