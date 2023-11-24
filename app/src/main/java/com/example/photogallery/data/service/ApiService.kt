import com.example.photogallery.domain.entities.PhotosDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    // change api_key not to be hardcoded
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=da9d38d3dee82ec8dda8bb0763bf5d9c&format=json&nojsoncallback=1")
    fun getPhotos(): Call<PhotosDataResponse>
}