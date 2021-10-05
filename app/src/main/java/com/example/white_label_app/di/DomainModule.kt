package com.example.white_label_app.di

import com.example.white_label_app.domain.usercase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindCreateProductUseCase(userCase: CreateProductUseCaseImpl): CreateProductUseCase

    @Binds
    fun bindGetProductUseCase(userCase: GetProductUseCaseImpl): GetProductsUseCase

    @Binds
    fun bindUploadProductImageUseCase(userCase: UploadProductImageUseCaseImpl): UploadProductImageUseCase
}