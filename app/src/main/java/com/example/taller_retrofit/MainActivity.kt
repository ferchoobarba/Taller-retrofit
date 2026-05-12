package com.example.taller_retrofit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taller_retrofit.ui.theme.Taller_retrofitTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

/**
 * Clase principal de la aplicación.
 * Es la "ventana" donde se dibuja todo lo que el usuario ve.
 */
class MainActivity : ComponentActivity() {
    // El adaptador que nos servirá de puente para obtener los datos
    private val mealDriverAdapter = MealDriverAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Permite que la app use toda la pantalla (hasta los bordes)
        setContent {
            // Aplicamos el tema visual de la aplicación
            Taller_retrofitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Caja contenedora que respeta los bordes del sistema
                    Box(modifier = Modifier.padding(innerPadding)) {
                        RecipeScreen(adapter = mealDriverAdapter)
                    }
                }
            }
        }
    }
}

/**
 * Pantalla principal que contiene el botón y los detalles de la receta.
 */
@Composable
fun RecipeScreen(adapter: MealDriverAdapter) {
    // Variables de estado: si cambian, la pantalla se actualiza sola
    var meal by remember { mutableStateOf<Meal?>(null) } // Guarda la receta actual
    var isLoading by remember { mutableStateOf(false) } // Indica si está cargando
    var hasError by remember { mutableStateOf(false) } // Indica si hubo un error

    Column(modifier = Modifier.fillMaxSize()) {
        // Botón para pedir una nueva receta
        Button(
            onClick = {
                isLoading = true // Empezamos a cargar
                hasError = false
                adapter.loadRandomMeal(
                    load = { meals ->
                        meal = meals.firstOrNull() // Guardamos la primera receta que llegue
                        isLoading = false // Terminamos de cargar
                    },
                    error = {
                        isLoading = false
                        hasError = true // Mostramos error si algo falla
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("🎲 Ver receta aleatoria")
        }

        // Si está cargando, mostramos un círculo de progreso
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (hasError) {
            // Si hay error, avisamos al usuario
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error al cargar la receta. Revisa tu conexión.")
            }
        } else {
            // Si hay una receta guardada, mostramos sus detalles
            meal?.let {
                RecipeDetail(it)
            }
        }
    }
}

/**
 * Dibuja toda la información detallada de una receta.
 */
@Composable
fun RecipeDetail(meal: Meal) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Permite deslizar hacia abajo
    ) {
        // Imagen del plato
        MealImage(imageUrl = meal.strMealThumb, modifier = Modifier.fillMaxWidth().height(250.dp))
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Título y Categoría
        Text(text = meal.strMeal, style = MaterialTheme.typography.headlineMedium)
        Text(text = "Categoría: ${meal.strCategory}", style = MaterialTheme.typography.bodyLarge)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Sección de Ingredientes
        Text(text = "Ingredientes:", style = MaterialTheme.typography.titleMedium)
        meal.getIngredients().forEach { (ingredient, measure) ->
            Text(
                text = "• $ingredient: $measure",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sección de Instrucciones
        Text(text = "Instrucciones:", style = MaterialTheme.typography.titleMedium)
        Text(
            text = meal.strInstructions ?: "Sin instrucciones disponibles.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

/**
 * Se encarga de descargar la imagen de internet y mostrarla.
 */
@Composable
fun MealImage(imageUrl: String, modifier: Modifier = Modifier) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    // Cada vez que la URL cambie, descargamos la imagen nueva
    LaunchedEffect(imageUrl) {
        bitmap = loadImage(imageUrl)
    }

    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = "Imagen de la receta",
            modifier = modifier
        )
    }
}

/**
 * Función técnica que descarga una imagen desde una URL.
 */
suspend fun loadImage(url: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.connect()
            BitmapFactory.decodeStream(connection.inputStream)
        } catch (ignored: Exception) {
            null // Si no puede cargar la imagen, devuelve nada
        }
    }
}

/**
 * Vista previa para el diseñador de Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Taller_retrofitTheme {
        RecipeScreen(MealDriverAdapter())
    }
}
