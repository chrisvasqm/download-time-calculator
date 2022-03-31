package com.chrisvasqm.speedy

import android.view.View
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import com.christianv07.dev.speedy.R
import com.christianv07.dev.speedy.ui.main.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.hasToString
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
                return isAssignableFrom(SeekBar::class.java)
            }

            override fun getDescription(): String {
                return ""
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SeekBar).progress = progress
            }
        }
    }

    @Test
    fun clearButton_WhenEveryFieldHasBeenChanged_ReturnThemToDefaultState() {
        launchActivity<MainActivity>().use {
            onView(withId(R.id.editFilesize))
                .perform(typeText("1"))

            onView(withId(R.id.spinnerFilesize))
                .perform(click())
            onData(hasToString("MB")).perform(click())

            onView(withId(R.id.editEstimatedSpeed))
                .perform(typeText("1"))

            onView(withId(R.id.spinnerEstimatedspeed))
                .perform(click())
            onData(hasToString("MB/s")).perform(click())

            onView(withId(R.id.seekbarDownloaded))
                .perform(withProgress(50))

            onView(withId(R.id.btnClear))
                .perform(click())

            onView(withId(R.id.editFilesize))
                .check(matches(withText("")))

            onView(withId(R.id.spinnerFilesize))
                .check(matches(withSpinnerText("KB")))

            onView(withId(R.id.editEstimatedSpeed))
                .check(matches(withText("")))

            onView(withId(R.id.spinnerEstimatedspeed))
                .check(matches(withSpinnerText("KB/s")))

            onView(withId(R.id.seekbarDownloaded))
                .check(matches(withSeekbarProgress(0)))
        }
    }

    private fun withSeekbarProgress(expectedProgress: Int): Matcher<View?> {
        return object : BoundedMatcher<View?, AppCompatSeekBar>(AppCompatSeekBar::class.java) {
            override fun describeTo(description: Description) {
                // Code block not needed
            }

            override fun matchesSafely(seekBar: AppCompatSeekBar): Boolean {
                return seekBar.progress == expectedProgress
            }
        }
    }
}