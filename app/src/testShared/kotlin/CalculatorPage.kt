package net.pot8os.kotlintestsample.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import net.pot8os.kotlintestsample.R

// create a page class for implementation of refactor AC test case page object model
class CalculatorPage {
    fun pressNumber(buttonId: Int): CalculatorPage {
        onView(withId(buttonId)).perform(click())
        return this
    }

    fun pressOperator(operatorId: Int): CalculatorPage {
        onView(withId(operatorId)).perform(click())
        return this
    }

    fun pressCalc(): CalculatorPage {
        onView(withId(R.id.button_calc)).perform(click())
        return this
    }

    fun pressAllClear(): CalculatorPage {
        onView(withId(R.id.button_all_clear)).perform(click())
        return this
    }

    fun assertResultIs(expected: String): CalculatorPage {
        onView(withId(R.id.field)).check(matches(withText(expected)))
        return this
    }
}