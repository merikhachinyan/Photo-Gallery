package com.example.photogallery.data.mapper
import com.example.photogallery.domain.entities.PhotoData
import com.example.photogallery.domain.entities.PhotoDataResponse

class PhotosResponseMapper : Mapper<PhotoDataResponse, PhotoData> {
    override fun map(s: PhotoDataResponse): PhotoData {
        return PhotoData(
            id = s.id,
            server = s.server,
            secret = s.secret
        )
    }
}