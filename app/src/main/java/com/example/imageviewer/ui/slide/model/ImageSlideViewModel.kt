package com.example.imageviewer.ui.slide.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.data.Repo

class ImageSlideViewModel(private val repo: Repo, private val data: ImageData) : ViewModel() {
    private val _images = MutableLiveData<List<ImageData>>()
    val images: LiveData<List<ImageData>>
        get() = _images

    private val _originalPos = MutableLiveData<Int>()
    val originalPos: LiveData<Int>
        get() = _originalPos

    init {
        initImages()
        initPosition()
    }

    private fun initImages() {
        _images.value = repo.getImagesData()
    }

    private fun initPosition() {
        _originalPos.value = _images.value?.indexOf(data)
    }
}