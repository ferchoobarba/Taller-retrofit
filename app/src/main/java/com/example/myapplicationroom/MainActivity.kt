package com.example.myapplicationroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplicationroom.ui.MealViewModel
import com.example.myapplicationroom.ui.ScreenOne
import com.example.myapplicationroom.ui.ScreenTwo
import com.example.myapplicationroom.ui.theme.MyApplicationRoomTheme

class MainActivity : ComponentActivity() {

    private val mealViewModel: MealViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationRoomTheme {
                val navController = rememberNavController()
                
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            ScreenOne(onNavigateToRecipe = {
                                mealViewModel.getRandomMeal()
                                navController.navigate("recipe")
                            })
                        }
                        composable("recipe") {
                            ScreenTwo(viewModel = mealViewModel)
                        }
                    }
                }
            }
        }
    }
}
