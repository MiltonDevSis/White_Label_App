package com.example.white_label_app.ui.products.addproduct

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.white_label_app.R
import com.example.white_label_app.domain.usercase.CreateProductUseCase
import com.example.white_label_app.utils.fromCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val createProductUseCase: CreateProductUseCase
) : ViewModel() {

    private val _imageUriErrorResId = MutableLiveData<Int>()
    val imageUriErrorResId: LiveData<Int> = _imageUriErrorResId

    private val _descriptionFieldErrorResId = MutableLiveData<Int?>()
    val descriptionFieldErrorResId: LiveData<Int?> = _descriptionFieldErrorResId

    private val _priceFieldErrorResId = MutableLiveData<Int>()
    val priceFieldErrorResId: LiveData<Int> = _priceFieldErrorResId

    private var isFormValid = false

    fun createProduct(description: String, price: String, imageUri: Uri?) = viewModelScope.launch {
        isFormValid = true

        _imageUriErrorResId.value = getDrawableResIdIfIsNull(imageUri)
        _descriptionFieldErrorResId.value = getErrorStringResIdIfIsEmpty(description)
        _priceFieldErrorResId.value = getErrorStringResIdIfIsEmpty(price)

        if (isFormValid) {
            try {
                val product = createProductUseCase(description, price.fromCurrency(), imageUri!!)
            } catch (e: Exception) {
                Log.d("CreateProduct", e.toString())
            }
        }
    }

    private fun getErrorStringResIdIfIsEmpty(value: String): Int? {
        return if (value.isEmpty()) {
            isFormValid = true
            R.string.add_product_field_error
        } else null
    }

    private fun getDrawableResIdIfIsNull(value: Uri?): Int {
        return if (value == null) {
            isFormValid = false
            R.drawable.background_product_image_error
        } else R.drawable.background_product_image
    }
}