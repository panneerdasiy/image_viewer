package com.example.imageviewer.ui.imagegrid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imageviewer.data.Repo
import com.google.gson.Gson

class MainViewModelFactory(private val gson: Gson, private val repo: Repo) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(gson, repo) as T
    }
}