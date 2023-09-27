package com.example.week3_lab.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.week3_lab.R
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Soal2() {

    var weight by rememberSaveable { mutableStateOf("") }
    var isBerat by rememberSaveable { mutableStateOf(true) }
    var height by rememberSaveable { mutableStateOf("") }
    var isTinggi by rememberSaveable { mutableStateOf(true) }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var morethanzero1 by rememberSaveable { mutableStateOf(true) }
    var morethanzero2 by rememberSaveable { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Image(
                    painter = painterResource(id = R.drawable.face),
                    contentDescription = "Face",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(top = 20.dp)

                )

                BeratInputField(
                    value = weight,
                    onValueChanged = { weight = it },
                    text = "Weight in KG",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    isDigit = isBerat,
                    moreThanZero = morethanzero1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                TinggiInputField(
                    value = height,
                    onValueChanged = { height = it },
                    text = "Height in CM",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    isDigit = isTinggi,
                    moreThanZero = morethanzero2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                Button(
                    onClick = {

                        isTinggi = isDigit(input = height)
                        isBerat = isDigit(input = weight)
                        morethanzero1 = moreThanZero(weight.toDouble())
                        morethanzero2 = moreThanZero(height.toDouble())

                        if (isBerat && isTinggi && morethanzero1 && morethanzero2) {
                            showDialog = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    enabled = weight.isNotBlank() && height.isNotBlank()
                ) {
                    Text(
                        text = "Calculate",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold

                    )
                }

                if (showDialog) {
                    Dialog(
                        onDismissRequest = {
                            showDialog = false
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .background(Color.White, shape = RoundedCornerShape(20.dp))
                                .fillMaxWidth(),


                            ) {
                            Text(
                                text = "Your BMI Analysis",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .padding(20.dp)
                            )

                            val tinggi = height.toDouble() / 100
                            val bmi = weight.toDouble() / (tinggi * tinggi)
                            val bmiFormat = String.format("%.1f", bmi)

                            Texts("Your Height : $tinggi m")
                            Texts("Your Weight : ${weight.toDouble()} kg")
                            Texts("Your BMI Score : $bmiFormat")

                            when (bmi) {
                                in 0.0..18.5 -> Texts("You are Under Weight")
                                in 18.6..24.9 -> Texts("You are Normal weight")
                                in 25.0..29.9 -> Texts("You are Overweight")
                                else -> Texts("You are Obese")

                            }
                            Button(
                                onClick = {
                                    showDialog = false
                                },
                                modifier = Modifier
                                    .padding(20.dp)
                                    .align(Alignment.End)
                            ) {
                                Text("OK")
                            }
                        }
                    }
                }


            }
        }
    )
}


@Composable
fun Texts(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier
            .padding(horizontal = 20.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeratInputField(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isDigit: Boolean,
    moreThanZero: Boolean
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
    } else if (!moreThanZero) {
        Text(
            text = "Weight must be Greater than 0",
            color = Color.Red,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TinggiInputField(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isDigit: Boolean,
    moreThanZero: Boolean
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

    else if (!moreThanZero) {
        Text(
            text = "Height must be Greater than 0",
            color = Color.Red,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
        )
    }
}

fun moreThanZero(input: Double): Boolean {
    return (input > 0)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Soal2Preview() {
    Soal2()
}