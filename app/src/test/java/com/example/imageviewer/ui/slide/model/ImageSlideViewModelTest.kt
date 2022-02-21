package com.example.imageviewer.ui.slide.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.imageviewer.data.AssetRepo
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.getOrAwaitValue
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImageSlideViewModelTest {

    @Mock
    private lateinit var repo: AssetRepo

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun initPosition_dataMatch_setZeroPos() {
        val data = ImageData()
        val model = ImageSlideViewModel(repo, data)

        Mockito.`when`(repo.getImagesData()).thenReturn(listOf(data))

        val pos = model.originalPos.getOrAwaitValue()
        Truth.assertThat(pos).isEqualTo(0)
    }

    @Test
    fun initPosition_noDataMatch_setNegativePos() {
        val model = ImageSlideViewModel(repo, ImageData())

        Mockito.`when`(repo.getImagesData()).thenReturn(listOf(ImageData()))

        val pos = model.originalPos.getOrAwaitValue()
        Truth.assertThat(pos).isLessThan(0)
    }
}