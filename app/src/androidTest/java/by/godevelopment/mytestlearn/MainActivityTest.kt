package by.godevelopment.mytestlearn

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

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

}
