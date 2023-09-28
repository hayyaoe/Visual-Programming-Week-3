package com.example.week3_lab.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week3_lab.R
import java.util.regex.Pattern


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun Soal1(){

    var base by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }

    var isDigit by rememberSaveable { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState()}

    Scaffold(
        snackbarHost = {SnackbarHost(snackbarHostState)},
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.play_arrow),
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .size(200.dp)
                        .rotate((-90f))
                )

                CustomTextField(
                    value = base,
                    onValueChanged = {base = it},
                    text = "Base",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp, vertical = 5.dp),
                    isDigit = isDigit

                )

                CustomTextField(
                    value = height,
                    onValueChanged = {height = it},
                    text = "Height",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp, vertical = 5.dp),
                    isDigit = isDigit

                )

                Text(
                    text = "The Triangle Area is :",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )

                if (base.isNotBlank() && height.isNotBlank()){

                    isDigit = isItADigit(input = base)
                    isDigit = isItADigit(input = height)

                    if (isItADigit(input = base) && isItADigit(input = height)){
                        val result = (base.toDouble() * height.toDouble())/2
                        Text(
                            text = result.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier
                        )
                    }else{
                        Text(
                            text = "Input Mustbe Digit",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier
                        )
                    }

                }else{
                    Text(
                        text = "0.0",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                    )
                }


            }
        }


    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isDigit : Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = text) },
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        shape = RoundedCornerShape(100),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Blue,
            textColor = Color.Blue
        ),
        isError = !isDigit
    )
    if (!isDigit) {
        Text(
            text = "Input must be Digit",
            color = Color.Red,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
        )
    }
}


fun isItADigit (input: String): Boolean{
    val inputPatterns = Pattern.compile(
        "^[-0-9]+$"
    )

    return inputPatterns.matcher(input).matches()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Soal1Preview(){
    Soal1()
}