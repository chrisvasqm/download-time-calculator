package com.chrisvasqm.speedy

import android.view.View
import android.widget.SeekBar
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.christianv07.dev.speedy.R
import com.christianv07.dev.speedy.ui.main.MainActivity
import org.hamcrest.Matcher
import org.junit.Test


class MainActivityTests {

    @Test
    internal fun twentyKbAtTenKBPerSecond_ResultsInTwoSeconds() {
        launchActivity<MainActivity>().use {
            onView(withId(R.id.editFilesize))
                .perform(typeText("20"))

            onView(withId(R.id.editEstimatedSpeed))
                .perform(typeText("10"))

            onView(withId(R.id.textViewResult))
                .check(matches(withText("0:0:2")))
        }
    }

    @Test
    fun twentyKbAtTenKBPerSecondWithFiftyPercentProgress_ReturnsOneSecond() {
        launchActivity<MainActivity>().use {
            onView(withId(R.id.editFilesize))
                .perform(typeText("20"))

            onView(withId(R.id.editEstimatedSpeed))
                .perform(typeText("10"))

            onView(withId(R.id.seekbarDownloaded))
                .perform(withProgress(50))

            onView(withId(R.id.textViewResult))
                .check(matches(withText("0:0:1")))
        }
    }

    private fun withProgress(progress: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(SeekBar::class.java)
            }

            override fun getDescription(): String {
                return ""
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SeekBar).progress = progress
            }
        }
    }
}