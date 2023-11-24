package com.example.photogallery.data.mapper

interface Mapper<S, R> {
    fun map(s: S): R
}