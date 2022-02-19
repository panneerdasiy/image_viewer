package com.example.imageviewer.ui.imagegrid

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imageviewer.data.AssetRepo
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private val images: MutableList<ImageData> = mutableListOf()
    private lateinit var adapter: ImagesAdapter
    private lateinit var binding: ActivityMainBinding
    private val model by viewModels<MainViewModel> {
        MainViewModelFactory(Gson(), AssetRepo(application.assets))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initImagesObserver()
    }

    private fun initRecyclerView() {
        adapter = ImagesAdapter()
        val layoutManager = GridLayoutManager(this, GRID_COLUMN_COUNT)
        binding.rlImageGrid.layoutManager = layoutManager
        binding.rlImageGrid.adapter = adapter
    }

    private fun initImagesObserver() {
        model.images.observe(this) {
            adapter.submitList(ArrayList<ImageData>(it))
        }
    }

    companion object {
        private const val GRID_COLUMN_COUNT = 2
    }
}