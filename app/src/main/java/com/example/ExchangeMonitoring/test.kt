package com.example.ExchangeMonitoring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


@Composable
fun Greeting(counter : State<Int>,
             name : String,
             onIncreaseClick : () ->Unit,
             onDecreaseClick : () ->Unit,
             modifier: Modifier = Modifier.fillMaxSize()) {
    val context = LocalContext.current
    println(context)
    Column(modifier = modifier){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .background(Color.White)) {
            Text(
                text = "Hello $name!",
                modifier = modifier
            )
            Text(
                text = "suuui ${counter.value}",
                modifier = modifier.background(Color.Red)
            )

        }
        Row {
            Button(
                modifier = modifier,
                onClick = onIncreaseClick
            ) {
                Text(text = "Increase",
                    color = Color.White
                )
            }
            Button(
                onClick = onDecreaseClick ){
                Text(text = "Decrease",
                    color = Color.White
                )
            }
        }
    }

}
