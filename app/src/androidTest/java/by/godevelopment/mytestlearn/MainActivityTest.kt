package by.godevelopment.mytestlearn

import android.app.Activity
import androidx.test.annotation.UiThreadTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private val testText = "test"

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun appLaunchesSuccessfully() {
//        Espresso
//            .onView(ViewMatchers.withId(R.id.button))
//            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.button))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.btn_text)))

        onView(withId(R.id.input_text)).check(matches(isDisplayed()))
        onView(withId(R.id.message)).check(matches(withText("null")))
    }

    @Test
    fun checkTextView() {
        onView(withId(R.id.input_text)).perform(typeText(testText), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.message)).check(matches(withText(testText)))
    }

//    @Test
//    fun checkToast() {
//        onView(withId(R.id.input_text)).perform(typeText(testText), closeSoftKeyboard())
//        onView(withId(R.id.button)).perform(click())
//        onView(withText(testText))
//            .inRoot(withDecorView(not(`is`(Activity().window.decorView))))
//            .check(matches(isDisplayed()))
//    }
}
