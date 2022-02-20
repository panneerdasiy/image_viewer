package com.example.imageviewer.data

interface Repo {
    fun getImagesData(): List<ImageData>
}