package com.jhonny.social

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jhonny.social.presenter.composables.CounterButton
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*



/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BasicComposableInstrumentedTest {
    private val drawableId = SemanticsPropertyKey<Int>("DrawableResId")

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.jhonny.PunkBeer", appContext.packageName)
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCounterButton() {
        composeTestRule.setContent {
            var valueCounter by remember {
                mutableStateOf(0)
            }
            CounterButton(
                value = valueCounter.toString(),
                onValueIncreaseClick = {
                    valueCounter += 1
                },
                onValueDecreaseClick = {
                    valueCounter = maxOf(valueCounter - 1, 0)
                },
                onValueClearClick = {
                    valueCounter = 0
                }
            )
        }

        composeTestRule.onNode(
            hasContentDescription("Increase count")
        ).performClick()
        composeTestRule.onNode(
            hasText("1")
        ).assertIsDisplayed()

        composeTestRule.onNode(
            hasContentDescription("Decrease count")
        ).performClick()
        composeTestRule.onNode(
            hasText("0")
        ).assertIsDisplayed()


    }

}
