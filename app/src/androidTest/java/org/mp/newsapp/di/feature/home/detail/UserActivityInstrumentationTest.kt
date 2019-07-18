package org.mp.newsapp.di.feature.home.detail

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.not
import org.mp.newsapp.R


@RunWith(AndroidJUnit4::class)
class UserActivityInstrumentationTest {
    @Rule
    @JvmField
    var rule = ActivityTestRule<UserActivity>(UserActivity::class.java)


    @Test
    @Throws(Exception::class)
    fun checkTextView_isDisplayed_and_notEmpty() {
        // passes if the textView does not match the empty string
        onView(withId(R.id.text_by)).check(matches(not(withText(""))))
        onView(withId(R.id.text_comments)).check(matches(not(withText(""))))
        onView(withId(R.id.text_kids)).check(matches(not(withText(""))))
        onView(withId(R.id.text_parent)).check(matches(not(withText(""))))
        onView(withId(R.id.text_time)).check(matches(not(withText(""))))
    }
}