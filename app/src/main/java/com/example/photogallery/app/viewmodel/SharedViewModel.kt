package com.example.photogallery.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _openPhotoShowFragment = MutableLiveData<String?>(null)
    val openPhotoShowFragment: LiveData<String?> = _openPhotoShowFragment

    fun showPhoto(url: String) {
        _openPhotoShowFragment.postValue(url)
    }
}