package com.example.imageviewer.ui.imagegrid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.data.Repo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainViewModel(private val gson: Gson, private val repo: Repo) : ViewModel() {
    private val _imagesJson = MutableLiveData("")
    private val _images = MediatorLiveData<List<ImageData>>()
    val images: LiveData<List<ImageData>>
        get() = _images


    init {
        initImagesJson()
        initImages()
    }

    private fun initImagesJson() {
        _imagesJson.value = repo.getImagesJson()
    }

    private fun initImages() {
        _images.addSource(_imagesJson) {
            if (it.isNotBlank()) {
                val type = object : TypeToken<List<ImageData>>() {}.type
                _images.value = gson.fromJson(it, type)
                _images.removeSource(_imagesJson)
            }
        }
    }
}