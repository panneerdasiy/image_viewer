package com.example.imageviewer.ui.slide.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.data.Repo

class ImageSlideViewModel(repo: Repo, private val data: ImageData) : BaseViewModel(repo) {
    private val _originalPos = MutableLiveData<Int>()
    val originalPos: LiveData<Int>
        get() = _originalPos

    init {
        initPosition()
    }

    private fun initPosition() {
        _originalPos.value = images.value?.indexOf(data)
    }
}