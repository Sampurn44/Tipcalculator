package com.example.tipcalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tipcalculator.ui.theme.TipcalculatorTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat
/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipcalculatorTheme {
                Surface (modifier = Modifier.fillMaxSize()){
                    TipTimeScreen()
                }
            }
        }
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")
        composeTestRule.onNodeWithText("Tip (%)").performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found."
        )
    }
}