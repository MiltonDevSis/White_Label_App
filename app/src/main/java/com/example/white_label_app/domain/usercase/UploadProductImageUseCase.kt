package com.example.white_label_app.domain.usercase

import android.net.Uri

interface UploadProductImageUseCase {

    suspend operator fun invoke(image: Uri) : String
}