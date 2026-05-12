package com.example.taller_retrofit

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Clase encargada de ejecutar las peticiones a la API en segundo plano.
 * Hereda de BaseServices para obtener la configuración de Retrofit.
 */
class MealServices : BaseServices() {

    /**
     * Llama a la API para obtener una receta aleatoria.
     * @param success Función que se ejecuta si la receta se carga correctamente.
     * @param catchError Función que se ejecuta si algo sale mal (ej: sin internet).
     */
    fun getRandomMeal(
        success: (data: List<Meal>) -> Unit,
        catchError: () -> Unit
    ) {
        // Ejecuta la petición en un hilo secundario para no congelar la pantalla
        viewModelScope.launch(Dispatchers.Main) {
            try {
                // Creamos la conexión usando la interfaz de Endpoints
                val resp = getRetrofit()
                    .create(MealEndpoints::class.java)
                    .getRandomMeal()

                // Si recibimos datos, los enviamos a la función "success"
                val body = resp.body()
                success(body?.meals ?: emptyList())
            } catch (e: Exception) {
                // Si hay un error, avisamos a la función "catchError"
                catchError()
            }
        }
    }
}
