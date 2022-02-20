package com.example.imageviewer.ui.slide.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.data.Repo

class ImageSlideViewModelFactory(private val repo: Repo, private val data: ImageData) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageSlideViewModel::class.java))
            return ImageSlideViewModel(repo, data) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}