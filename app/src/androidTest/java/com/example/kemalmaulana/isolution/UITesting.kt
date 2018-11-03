package com.example.kemalmaulana.isolution

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.kemalmaulana.isolution.R.id.*
import com.example.kemalmaulana.isolution.view.activity.SplashActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITesting {

    @Rule
    @JvmField var activityRule = ActivityTestRule(SplashActivity::class.java)

    @Before
    fun init() {
        activityRule.launchActivity(Intent(activityRule.activity, SplashActivity::class.java))
    }

    @Test
    fun instrumentationTest() {
        Thread.sleep(5000)
        onView(withId(imageKehadiran)).check(matches(isDisplayed()))
                .perform(click())
        Thread.sleep(5000)
        pressBack()
    }

}
