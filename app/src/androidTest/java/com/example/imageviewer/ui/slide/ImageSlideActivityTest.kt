package com.example.imageviewer.ui.slide

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.imageviewer.R
import com.example.imageviewer.Util
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.ui.slide.ImageSlideActivity.Companion.IMAGE_DATA
import com.google.gson.Gson
import org.junit.Rule
import org.junit.Test

class ImageSlideActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<ImageSlideActivity> = ActivityScenarioRule(getMyIntent())

    private fun getMyIntent(): Intent {
        val intent =
            Intent(ApplicationProvider.getApplicationContext(), ImageSlideActivity::class.java)
        intent.putExtra(
            IMAGE_DATA, Gson().fromJson(
                "{\n" +
                        "        \"copyright\": \"Ivan Pedretti\",\n" +
                        "        \"date\": \"2019-12-04\",\n" +
                        "        \"explanation\": \"It may appear, at first, like the Galaxy is producing the lightning, but really it's the Earth. The featured nighttime landscape was taken from a southern tip of the Italian Island of Sardinia in early June. The foreground rocks and shrubs are near the famous Capo Spartivento Lighthouse, and the camera is pointed south toward Algeria in Africa.  In the distance, across the Mediterranean Sea, a thunderstorm is threatening, with several electric lightning strokes caught together during this 25-second wide-angle exposure.  Much farther in the distance, strewn about the sky, are hundreds of stars in the neighborhood of our Sun in the Milky Way Galaxy. Farthest away, and slanting down from the upper left, are billions of stars that together compose the central band of our Milky Way.   Free Lecture: APOD editor to speak in NYC on January 3\",\n" +
                        "        \"hdurl\": \"https://apod.nasa.gov/apod/image/1912/ElectricMilkyWay_Pedretti_1920.jpg\",\n" +
                        "        \"media_type\": \"image\",\n" +
                        "        \"service_version\": \"v1\",\n" +
                        "        \"title\": \"Electric Night\",\n" +
                        "        \"url\": \"https://apod.nasa.gov/apod/image/1912/ElectricMilkyWay_Pedretti_1080.jpg\"\n" +
                        "    }", ImageData::class.java
            )
        )
        return intent
    }

    @Test
    fun imageGrid_textShown() {
        Espresso.onView(ViewMatchers.withId(R.id.pager))
            .check(
                ViewAssertions.matches(
                    Util.atPosition(
                        2,
                        ViewMatchers.hasDescendant(ViewMatchers.withText("Electric Night"))
                    )
                )
            )
    }

    @Test
    fun imageGrid_imageShown() {
        Espresso.onView(ViewMatchers.withId(R.id.pager))
            .check(
                ViewAssertions.matches(
                    Util.atPosition(
                        2,
                        ViewMatchers.hasDescendant(ViewMatchers.withId(R.id.imageView))
                    )
                )
            )
    }
}