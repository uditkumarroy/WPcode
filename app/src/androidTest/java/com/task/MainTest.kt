package com.task

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.task.ui.BlogFragment
import com.task.ui.MainActivity
import com.task.ui.MainFragmentFactory
import com.task.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainTest {

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun test_sample() {
        hiltRule.inject()
    }

    @Test
    fun mainActivityLaunchTest() {
        //  val scenario = launchActivity<MainActivity>()
        onView(withId(R.id.main_lay)).check(matches(isDisplayed()))
    }

    @Test
    fun test_main_fragment() {
        val scenario = launchFragmentInHiltContainer<BlogFragment>(
            factory = fragmentFactory
        )
        onView(withId(R.id.blogs_main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_recycler_fragment() {
        val scenario = launchFragmentInHiltContainer<BlogFragment>(
            factory = fragmentFactory
        )
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun itemInMiddleOfList_hasSpecialText() {

    }


}