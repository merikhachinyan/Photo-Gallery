sealed class PhotosResult<out  T: Any> {
    class Success<out  T: Any>(val data: T): PhotosResult<T>()
    class Failure(val exception: Exception): PhotosResult<Nothing>()
}