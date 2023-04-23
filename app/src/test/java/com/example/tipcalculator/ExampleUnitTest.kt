package com.example.tipcalculator

import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
import java.text.NumberFormat
import org.junit.Assert.assertEquals
class TipCalculatorTests {
    @Test
    fun calculate_20_percent_tip_no_roundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(Amount = amount, tipPercentage = tipPercent, Roundup = false)
        assertEquals(expectedTip, actualTip)
    }
}