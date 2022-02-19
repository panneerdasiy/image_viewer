package com.example.imageviewer.imagegrid

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.data.Repo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainViewModel(private val gson: Gson, private val repo: Repo) : ViewModel() {
    private val _imagesJson = MutableLiveData("")
    private val images = MediatorLiveData<List<ImageData>>()

    init {
        initImagesJson()
        initImages()
    }

    private fun initImagesJson() {
        _imagesJson.value = repo.getImagesJson()
    }

    private fun initImages() {
        images.addSource(_imagesJson) {
            val type = object : TypeToken<List<ImageData>>() {}.type
            images.value = gson.fromJson(it, type)
            images.removeSource(_imagesJson)
        }
    }
}