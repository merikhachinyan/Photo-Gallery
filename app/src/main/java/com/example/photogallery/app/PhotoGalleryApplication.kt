package com.example.photogallery.app

import android.app.Application
import com.example.photogallery.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PhotoGalleryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PhotoGalleryApplication)
            modules(module)
        }
    }
}