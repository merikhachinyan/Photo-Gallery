import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photogallery.domain.entities.PhotoData
import com.example.photogallery.domain.repo.GetPhotosDataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PhotosViewModel(private val getPhotosDataRepo: GetPhotosDataRepo) : ViewModel() {

    private val _photosStateFlow = MutableStateFlow<List<PhotoData>>(emptyList())
    val photosStateFlow: StateFlow<List<PhotoData>> = _photosStateFlow

    private val _errorStateFlow = MutableStateFlow<String?>(null)
    val errorStateFlow: StateFlow<String?> = _errorStateFlow

    private var photosList = mutableListOf<PhotoData>()

    fun fetchPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            getPhotosDataRepo.getPhotos().onEach {
                when (it) {
                    is PhotosResult.Success -> {
                        photosList = it.data.toMutableList()
                        _photosStateFlow.tryEmit(photosList)
                        if (it.data.isEmpty()) {
                            _errorStateFlow.tryEmit(NO_PHOTOS_FOUND)
                        }
                    }

                    is PhotosResult.Failure -> {
                        _errorStateFlow.tryEmit(ERROR_MESSAGE)
                    }

                    else -> {}
                }
            }.launchIn(viewModelScope)
        }
    }

    fun emitError(errorMsg: String?) {
        _errorStateFlow.tryEmit(errorMsg)
    }

    companion object {
        private const val ERROR_MESSAGE = "An error occurred"
        private const val NO_PHOTOS_FOUND = "No Photos Found"
    }
}