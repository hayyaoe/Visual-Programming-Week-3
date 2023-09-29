package com.example.week3_lab.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
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
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun Soal3(){
    var name by rememberSaveable { mutableStateOf("") }
    var usia by rememberSaveable { mutableStateOf("") }
    var umur by rememberSaveable { mutableIntStateOf(0) }
    var isUsia by rememberSaveable { mutableStateOf(true) }
    var isMoreThanZero by rememberSaveable { mutableStateOf(true) }
    var showText by rememberSaveable { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState)}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
            ){
                Text(
                    text = "AgeCalculator",
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
                    painter = painterResource(id = R.drawable.outline_sentiment_satisfied_24),
                    contentDescription = "face",
                    modifier = Modifier
                        .size(150.dp)
                )

                TextField(
                    value = name,
                    onValueChanged = {name = it},
                    text = "Enter Your Name",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                    ),
                    modifier = Modifier
                        .padding(vertical = 5.dp)

                )

                TinggiInputField(
                    value = usia,
                    onValueChanged = {usia = it},
                    text = "Enter Your Birth Year",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isDigit = isUsia,
                    moreThanZero =  isMoreThanZero,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                )

                if(usia.isNotBlank()){
                    isUsia = isItADigit(input = usia)
                }else{
                    showText = false
                }

                if(usia.isNotBlank()){
                    isUsia = isItADigit(input = usia)
                }else{
                    showText= false
                }

                Button(
                    onClick = {


                        isMoreThanZero = moreThanZero(input = usia.toDouble())
                        umur = Calendar.getInstance().get(Calendar.YEAR) - usia.toInt()
                        showText = isUsia && isMoreThanZero

                        if (showText){
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Hi, $name! Your Age is $umur"
                                )
                            }
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 55.dp),
                    enabled = usia.isNotBlank() && name.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(Color.Cyan,Color.White,Color.LightGray, Color.White)
                ) {
                    Text(
                        text = "Calculate Your Age",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 5.dp)

                    )
                }

                if(showText){
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        modifier = Modifier
                            .padding(20.dp)
                            .border(BorderStroke(2.dp, color = Color.Blue), RoundedCornerShape(50))
                    ) {
                        Text(
                            text = "Hi, $name! Your Age is $umur",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
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
    )
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal3Preview(){
    Soal3()
}
