package com.example.imageviewer.ui.grid.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imageviewer.data.Repo

class MainViewModelFactory(private val repo: Repo) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(repo) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}