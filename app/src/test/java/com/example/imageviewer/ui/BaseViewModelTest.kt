package com.example.imageviewer.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.imageviewer.data.AssetRepo
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.getOrAwaitValue
import com.example.imageviewer.ui.slide.model.BaseViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BaseViewModelTest {

    @Mock
    private lateinit var repo: AssetRepo

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun initImages_setsNonEmptyList() {
        val model = BaseViewModel(repo)

        `when`(repo.getImagesData()).thenReturn(listOf(ImageData()))

        val list = model.images.getOrAwaitValue()
        assertThat(list).isNotEmpty()
    }

    @Test
    fun initImages_setsEmptyList() {
        val model = BaseViewModel(repo)

        `when`(repo.getImagesData()).thenReturn(emptyList())

        val list = model.images.getOrAwaitValue()
        assertThat(list).isEmpty()
    }

}