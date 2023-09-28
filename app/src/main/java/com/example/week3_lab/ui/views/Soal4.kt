package com.example.week3_lab.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week3_lab.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun Soal4() {
    var nilai1 by rememberSaveable { mutableStateOf("") }
    var nilai2 by rememberSaveable { mutableStateOf("") }
    var nilai3 by rememberSaveable { mutableStateOf("") }
    var isNilai1 by rememberSaveable { mutableStateOf(true) }
    var isNilai2 by rememberSaveable { mutableStateOf(true) }
    var isNilai3 by rememberSaveable { mutableStateOf(true) }

    var moreThanZero1 by rememberSaveable { mutableStateOf(true) }
    var moreThanZero2 by rememberSaveable { mutableStateOf(true) }
    var moreThanZero3 by rememberSaveable { mutableStateOf(true) }

    var averageScore by rememberSaveable { mutableStateOf(0.0) }

    var showText by rememberSaveable { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
            ) {
                Text(
                    text = "Student Score",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo_uc_fix_sep_2021_01__1_),
                    contentDescription = "UC Logo",
                    modifier = Modifier
                        .size(150.dp)
                )

                MoreThanZeroInputField(
                    value = nilai1,
                    onValueChanged = { nilai1 = it },
                    text = "Buddy's Score",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    ),
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    isDigit = isNilai1,
                    moreThanZero = moreThanZero1
                )

                MoreThanZeroInputField(
                    value = nilai2,
                    onValueChanged = { nilai2 = it },
                    text = "Mikey's Score",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    ),
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    isDigit = isNilai2,
                    moreThanZero = moreThanZero2
                )

                MoreThanZeroInputField(
                    value = nilai3,
                    onValueChanged = { nilai3 = it },
                    text = "Bonno's Score",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    ),
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    isDigit = isNilai3,
                    moreThanZero = moreThanZero3
                )

                if (nilai1.isNotBlank()) {
                    isNilai1 = isItADigit(input = nilai1)
                } else {
                    showText = false
                }

                if (nilai2.isNotBlank()) {
                    isNilai2 = isItADigit(input = nilai2)
                } else {
                    showText = false
                }

                if (nilai3.isNotBlank()) {
                    isNilai3 = isItADigit(input = nilai3)
                } else {
                    showText = false
                }


                Button(
                    onClick = {
                        moreThanZero1 = range(input = nilai1.toDouble())
                        moreThanZero2 = range(input = nilai2.toDouble())
                        moreThanZero3 = range(input = nilai3.toDouble())

                        showText =
                            isNilai1 && moreThanZero1 && isNilai2 && moreThanZero2 && isNilai3 && moreThanZero3

                        if (showText) {
                            averageScore = (nilai1.toDouble() + nilai2.toDouble() + nilai3.toDouble()) / 3
                            scope.launch {
                                when (averageScore) {
                                    in 0.0..69.0 -> snackbarHostState.showSnackbar("Siswa perlu diberi soal tambahan")
                                    in 70.0..100.0 -> snackbarHostState.showSnackbar("Siswa mengerti pembelajaran ")
                                    else -> snackbarHostState.showSnackbar("Miss calculation")
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 55.dp),
                    enabled = nilai1.isNotBlank() && nilai2.isNotBlank() && nilai3.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(
                        Color.Cyan,
                        Color.White,
                        Color.LightGray, Color.White
                    )

                ) {
                    Text(
                        text = "Calculate Average",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 5.dp)

                    )
                }

                if (showText) {
                    Text(
                        text = "Average Score :  ${String.format("%.2f", averageScore)}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 5.dp)

                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreThanZeroInputField(
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
            text = "Input must be in range 0-100",
            color = Color.Red,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
        )
    }
}

fun range(input: Double?): Boolean {
    if (input == null) {
        return false
    }
    return (input in 0.0..100.0)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Soal4Preview() {
    Soal4()
}