package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipcalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipcalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

@Composable
fun TipTimeScreen(){
    var amountInput by remember { mutableStateOf("") }
    val amount= amountInput.toDoubleOrNull()?:0.0
    var tipInput by remember { mutableStateOf("") }
    val tipPercentage= tipInput.toDoubleOrNull()?:0.0
    val tip = CalculateTip(amount,tipPercentage)

    Column(modifier = Modifier.padding(32.dp), verticalArrangement = Arrangement.spacedBy(8.dp) ) {
        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontSize = 24.sp ,
            modifier= Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditNumber(label=R.string.bill_amount, value=amountInput, onValueChange = {amountInput=it})
        Spacer(modifier = Modifier.height(16.dp))
        EditNumber(label=R.string.how_service, value=tipInput, onValueChange = {tipInput=it})
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id=R.string.tip_amount,tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumber(@StringRes label: Int, value: String, onValueChange:(String)-> Unit,modifier: Modifier=Modifier ){

    TextField(
        value = value,
        onValueChange =onValueChange,
        label = { Text(
            text = stringResource(id = label),
            modifier = Modifier.fillMaxWidth() )},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}
private fun CalculateTip(Amount:Double,tipPercentage : Double=15.0): String
{
val tip = (tipPercentage/100) * Amount
   return NumberFormat.getCurrencyInstance().format(tip)

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipcalculatorTheme {
        TipTimeScreen()
    }
}