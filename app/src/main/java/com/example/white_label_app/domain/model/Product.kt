package com.example.white_label_app.domain.model

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id : String = "",
    val description: String = "",
    val price: Double = 0.0,
    @get:PropertyName("image_url")
    @set:PropertyName("iimage_url")
    var imageUrl: String = ""
) : Parcelable
