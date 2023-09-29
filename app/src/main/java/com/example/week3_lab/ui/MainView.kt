package com.example.week3_lab.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun MainView(navController: NavController){

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Buttons(navController = navController, title = "soal1", pageTitle = "Soal 1")
        Buttons(navController = navController, title = "soal2", pageTitle = "Soal 2")
        Buttons(navController = navController, title = "soal3", pageTitle = "Soal 3")
        Buttons(navController = navController, title = "soal4", pageTitle = "Soal 4")
    }
}

@Composable
fun Buttons(
    navController: NavController,
    title: String,
    pageTitle: String
){

    Button(
        onClick = {
            navController.navigate(title)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = pageTitle,
            color = Color.White

        )
    }
}