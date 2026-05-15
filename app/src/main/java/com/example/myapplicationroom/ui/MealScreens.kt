package com.example.myapplicationroom.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplicationroom.network.Meal

@Composable
fun ScreenOne(onNavigateToRecipe: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Recetas El Buen Sabor", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onNavigateToRecipe) {
            Text(text = "Cargar receta")
        }
    }
}

@Composable
fun ScreenTwo(viewModel: MealViewModel) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is MealState.Idle -> {
                Text(text = "Presiona el botón para cargar", modifier = Modifier.align(Alignment.Center))
            }
            is MealState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is MealState.Success -> {
                RecipeDetail(meal = state.meal)
            }
            is MealState.Error -> {
                Text(text = state.message, color = MaterialTheme.colorScheme.error, modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun RecipeDetail(meal: Meal) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = meal.strMeal, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = meal.strMeal,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            // Para alinear a la izquierda dentro de un LazyColumn centrado, usamos un Box que ocupe todo el ancho
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Ingredientes",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(meal.getIngredientsList()) { ingredient ->
            Text(
                text = "• $ingredient",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Proceso de la receta",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = meal.strInstructions)
            
            meal.strYoutube?.let { url ->
                if (url.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }) {
                        Text(text = "Ver tutorial en YouTube")
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
