package com.example.imageviewer.ui.grid

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imageviewer.IVApp
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.databinding.ActivityMainBinding
import com.example.imageviewer.ui.grid.model.MainViewModel
import com.example.imageviewer.ui.grid.model.MainViewModelFactory
import com.example.imageviewer.ui.slide.ImageSlideActivity


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ImagesAdapter
    private lateinit var binding: ActivityMainBinding
    private val model by viewModels<MainViewModel> {
        MainViewModelFactory(IVApp.repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        setToolbar()
        initRecyclerView()
        initImagesObserver()
    }

    private fun setContentView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initRecyclerView() {
        adapter = ImagesAdapter { view, data ->
            openImageSlider(view, data)
        }
        val layoutManager = GridLayoutManager(this, GRID_COLUMN_COUNT)
        binding.rlImageGrid.layoutManager = layoutManager
        binding.rlImageGrid.adapter = adapter
    }

    private fun openImageSlider(view: View, data: ImageData) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this@MainActivity, view, view.transitionName
        )
        ImageSlideActivity.start(this, data, options)
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