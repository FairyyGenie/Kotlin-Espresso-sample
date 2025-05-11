package net.pot8os.kotlintestsample

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import java.text.DecimalFormat

import net.pot8os.kotlintestsample.pages.CalculatorPage

/**
 * @author So Nakamura, 2020-Feb-15
 */
abstract class CalculatorSpec {

    @Before
    fun setup() {
        launchFragmentInContainer<CalculatorFragment>(themeResId = R.style.Theme_MyApp)
    }

    @Test
    fun testSum() {
        onView(withId(R.id.button_1)).perform(click())
        onView(withId(R.id.button_2)).perform(click())
        onView(withId(R.id.button_3)).perform(click())
        onView(withId(R.id.button_sum)).perform(click())
        onView(withId(R.id.button_3)).perform(click())
        onView(withId(R.id.button_2)).perform(click())
        onView(withId(R.id.button_1)).perform(click())
        onView(withId(R.id.button_calc)).perform(click())
        onView(withId(R.id.field)).check(matches(withText("${123 + 321}")))
    }

    @Test
    fun testSub() {
        onView(withId(R.id.button_9)).perform(click())
        onView(withId(R.id.button_9)).perform(click())
        onView(withId(R.id.button_9)).perform(click())
        onView(withId(R.id.button_sub)).perform(click())
        onView(withId(R.id.button_3)).perform(click())
        onView(withId(R.id.button_3)).perform(click())
        onView(withId(R.id.button_3)).perform(click())
        onView(withId(R.id.button_calc)).perform(click())
        onView(withId(R.id.field)).check(matches(withText("${999 - 333}")))
    }

    @Test
    fun testMul() {
        onView(withId(R.id.button_1)).perform(click())
        onView(withId(R.id.button_0)).perform(click())
        onView(withId(R.id.button_0)).perform(click())
        onView(withId(R.id.button_mul)).perform(click())
        onView(withId(R.id.button_2)).perform(click())
        onView(withId(R.id.button_0)).perform(click())
        onView(withId(R.id.button_0)).perform(click())
        onView(withId(R.id.button_calc)).perform(click())
        val formatter = DecimalFormat("#,###")
        onView(withId(R.id.field)).check(matches(withText(formatter.format(100 * 200))))
    }

    @Test
    fun testDiv() {
        onView(withId(R.id.button_3)).perform(click())
        onView(withId(R.id.button_3)).perform(click())
        onView(withId(R.id.button_3)).perform(click())
        onView(withId(R.id.button_div)).perform(click())
        onView(withId(R.id.button_1)).perform(click())
        onView(withId(R.id.button_0)).perform(click())
        onView(withId(R.id.button_0)).perform(click())
        onView(withId(R.id.button_calc)).perform(click())
        onView(withId(R.id.field)).check(matches(withText("${333 / 100.0}")))
    }

    /*@Test
    fun testAllClear() {
        // Perform a calculation first
        onView(withId(R.id.button_2)).perform(click())
        onView(withId(R.id.button_0)).perform(click()) // 20
        onView(withId(R.id.button_sum)).perform(click())
        onView(withId(R.id.button_5)).perform(click()) // +5
        onView(withId(R.id.button_calc)).perform(click())

        // Tap the AC button
        onView(withId(R.id.button_all_clear)).perform(click())

        // Verify that the field reset to "0"
        onView(withId(R.id.field)).check(matches(withText("0")))
    }*/
    // above is the none refactored version of AC test

    @Test
    fun testAllClear_usingPageObject() {
        val calculator = CalculatorPage()

        calculator
            .pressNumber(R.id.button_2)
            .pressNumber(R.id.button_0) // 20
            .pressOperator(R.id.button_sum)
            .pressNumber(R.id.button_5) // +5
            .pressCalc()
            .pressAllClear()
            .assertResultIs("0")
    } // refactored using Page Object Model
}
