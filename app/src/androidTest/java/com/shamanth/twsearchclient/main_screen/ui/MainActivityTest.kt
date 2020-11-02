package com.shamanth.twsearchclient.main_screen.ui

import android.app.Activity
import android.app.LauncherActivity
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.TypeTextAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.shamanth.twsearchclient.R
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    @get:Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java,true,true)

    private fun getActivity() = activityRule.activity
    @Test
    fun getNoData(){
        onView(withId(R.id.search_query)).perform(typeText(""))
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.no_data)).check(matches(isDisplayed()))
    }

    @Test
    fun getData(){
        onView(withId(R.id.search_query)).perform(typeText(""))
        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.no_data)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}