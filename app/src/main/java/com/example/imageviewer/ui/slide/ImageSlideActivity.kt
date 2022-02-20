package com.example.imageviewer.ui.slide

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.example.imageviewer.IVApp
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.databinding.ActivityImageSlideBinding
import com.example.imageviewer.ui.slide.model.ImageSlideViewModel
import com.example.imageviewer.ui.slide.model.ImageSlideViewModelFactory
import com.squareup.picasso.Callback


class ImageSlideActivity : AppCompatActivity() {
    private lateinit var adapter: ImageSlideAdapter
    private lateinit var binding: ActivityImageSlideBinding
    private val model by viewModels<ImageSlideViewModel> {
        ImageSlideViewModelFactory(IVApp.repo, intent.getParcelableExtra(IMAGE_DATA) ?: ImageData())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        postponeEnterTransition()
        setToolbar()
        initViewPager()
        initImagesObserver()
        initImagePositionObserver()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setContentView() {
        binding = ActivityImageSlideBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initViewPager() {
        adapter = ImageSlideAdapter(object : Callback {
            override fun onSuccess() {
                startPostponedEnterTransition()
            }

            override fun onError(e: Exception?) {
                startPostponedEnterTransition()
            }
        })
        binding.pager.adapter = adapter
    }

    private fun initImagesObserver() {
        model.images.observe(this) {
            adapter.submitList(ArrayList<ImageData>(it))
        }
    }

    private fun initImagePositionObserver() {
        model.originalPos.observe(this) {
            binding.pager.setCurrentItem(it, false)
        }
    }

    override fun onBackPressed() {
        finish()
    }

    companion object {
        private const val IMAGE_DATA = "IMAGE_DATA"

        fun start(activity: Activity, data: ImageData, options: ActivityOptionsCompat? = null) {
            val intent = Intent(activity, ImageSlideActivity::class.java)
            intent.putExtra(IMAGE_DATA, data)
            activity.startActivity(intent, options?.toBundle())
        }
    }
}