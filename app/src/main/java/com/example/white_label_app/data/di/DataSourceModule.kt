package com.example.white_label_app.data.di

import com.example.white_label_app.data.FirebaseProductDataSource
import com.example.white_label_app.data.ProductDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun bindProductDatasource(dataSource: FirebaseProductDataSource): ProductDataSource
}