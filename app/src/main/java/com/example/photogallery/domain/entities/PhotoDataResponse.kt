package com.example.photogallery.domain.entities

import com.google.gson.annotations.SerializedName

data class PhotosDataResponse(
    @field:SerializedName("photos")
    val photos: PhotoResponse? = null
)

data class PhotoResponse(
    @field:SerializedName("photo")
    val photo: List<PhotoDataResponse>? = null
)

data class PhotoDataResponse (
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("server")
    val server: String? = null,

    @field:SerializedName("secret")
    val secret: String? = null,
)