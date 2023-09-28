package com.example.week3_lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.week3_lab.ui.theme.Week3_LabTheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.week3_lab.ui.views.Soal1


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week3_LabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting()
                }
            }
        }
    }
}

//@Composable
//fun Greeting() {
//    val navController = rememberNavController()
//
//    NavHost(navController, startDestination = "screen1") {
//        composable("screen1") { Screen1(navController) }
//        composable("screen2") { Screen2(navController) }
//        // Define more screens as needed
//    }
//}
//
//@Composable
//fun Screen2(navController: NavController) {
//    Soal2()
//    Button(onClick = { navController.navigate("screen1") }) {
//        Text("Go to Screen 1")
//    }
//}
//
//@Composable
//fun Screen1(navController: NavController) {
//    Soal1()
//    Button(
//        onClick = {
//            navController.navigate("screen2")
//        }
//    ) {
//        Text("Go to Screen 2")
//    }
//}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Week3_LabTheme {
//        Greeting()
    }
}