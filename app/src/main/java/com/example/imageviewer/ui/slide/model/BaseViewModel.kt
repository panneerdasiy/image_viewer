package com.example.imageviewer.ui.slide.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.data.Repo

open class BaseViewModel(private val repo: Repo): ViewModel() {
    private val _images = MutableLiveData<List<ImageData>>()
    val images: LiveData<List<ImageData>>
        get() = _images

    init {
        initImages()
    }

    private fun initImages() {
        _images.value = repo.getImagesData()
    }
}
