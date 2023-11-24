package com.example.photogallery.data.repo

import ApiConfig
import PhotosResult
import com.example.photogallery.data.mapper.PhotosResponseMapper
import com.example.photogallery.domain.entities.PhotoData
import com.example.photogallery.domain.entities.PhotosDataResponse
import com.example.photogallery.domain.repo.GetPhotosDataRepo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPhotosDataRepoImpl(private val apiConfig: ApiConfig, val mapper: PhotosResponseMapper): GetPhotosDataRepo {
    override suspend fun getPhotos(): Flow<PhotosResult<List<PhotoData>>> =
        callbackFlow {
            apiConfig.getApiService().getPhotos().enqueue(
                object : Callback<PhotosDataResponse> {
                    override fun onResponse(
                        call: Call<PhotosDataResponse>,
                        response: Response<PhotosDataResponse>
                    ) {
                        if (response.isSuccessful) {
                            val photosResponse = response.body()?.photos?.photo ?: emptyList()
                            val images = photosResponse.map { mapper.map(it)  }
                            trySend(PhotosResult.Success(images))
                        } else {
                            trySend(PhotosResult.Failure(Exception(response.message())))
                        }
                    }

                    override fun onFailure(call: Call<PhotosDataResponse>, t: Throwable) {
                        trySend(PhotosResult.Failure(Exception(t.message)))
                    }

                }
            )
            awaitClose()
        }
}