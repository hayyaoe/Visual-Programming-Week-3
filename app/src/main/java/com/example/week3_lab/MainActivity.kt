package com.example.week3_lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.compose.ui.Modifier
import com.example.week3_lab.ui.theme.Week3_LabTheme
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week3_lab.ui.MainView
import com.example.week3_lab.ui.views.Soal1
import com.example.week3_lab.ui.views.Soal2
import com.example.week3_lab.ui.views.Soal3
import com.example.week3_lab.ui.views.Soal4

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week3_LabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "home"
                        ) {
                            composable("home") {
                                MainView(navController = navController)
                            }
                            composable("soal1") {
                                Soal1()
                            }
                            composable("soal2") {
                                Soal2()
                            }
                            composable("soal3") {
                                Soal3()
                            }
                            composable("soal4") {
                                Soal4()
                            }
                        }
                    }
                }
            }
        }
    }
}

