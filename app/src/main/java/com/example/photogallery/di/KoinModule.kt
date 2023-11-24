package com.example.photogallery.di

import ApiConfig
import PhotosViewModel
import com.example.photogallery.app.viewmodel.SharedViewModel
import com.example.photogallery.data.mapper.PhotosResponseMapper
import com.example.photogallery.data.repo.GetPhotosDataRepoImpl
import com.example.photogallery.domain.repo.GetPhotosDataRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    factory { ApiConfig() }
    factory { PhotosResponseMapper() }
    factory<GetPhotosDataRepo> { GetPhotosDataRepoImpl(apiConfig = get(), mapper = get()) }
    viewModel { PhotosViewModel(getPhotosDataRepo = get()) }

    // if there will be time I will change single to the scoped
    single { SharedViewModel() }
}