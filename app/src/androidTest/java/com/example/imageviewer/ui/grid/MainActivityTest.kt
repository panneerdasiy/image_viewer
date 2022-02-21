package com.example.imageviewer.ui.grid

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.imageviewer.R
import com.example.imageviewer.Util
import com.example.imageviewer.ui.slide.ImageSlideActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickImage_startImageSlideScreen() {
        onView(withId(R.id.rlImageGrid))
            .perform(
                RecyclerViewActions.actionOnItem<ImagesAdapter.ImageViewHolder>(
                    hasDescendant(withId(R.id.imageView)), click()
                )
            )

        intended(hasComponent(ImageSlideActivity::class.java.name))
    }

    @Test
    fun imageGrid_textShown() {
        onView(withId(R.id.rlImageGrid))
            .check(matches(Util.atPosition(2, hasDescendant(withText("Electric Night")))))
    }

    @Test
    fun imageGrid_imageShown() {
        onView(withId(R.id.rlImageGrid))
            .check(matches(Util.atPosition(2, hasDescendant(withId(R.id.imageView)))))
    }
}