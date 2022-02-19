package com.example.imageviewer.imagegrid

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _imagesJson = MutableLiveData("")
    private val images = MediatorLiveData<List<ImageData>>
}