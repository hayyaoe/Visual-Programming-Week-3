package com.example.week3_lab.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun Soal1(){

    var base by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState()}
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {SnackbarHost(snackbarHostState)},
        content = {
            Column {

            }
        }
    )

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Soal1Preview(){
    Soal1()
}