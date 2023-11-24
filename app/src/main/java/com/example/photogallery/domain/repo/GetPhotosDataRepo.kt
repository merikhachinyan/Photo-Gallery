package com.example.photogallery.domain.repo

import PhotosResult
import com.example.photogallery.domain.entities.PhotoData
import kotlinx.coroutines.flow.Flow

interface GetPhotosDataRepo {
    suspend fun getPhotos(): Flow<PhotosResult<List<PhotoData>>>
}