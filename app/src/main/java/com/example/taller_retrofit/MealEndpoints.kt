package com.example.taller_retrofit

import retrofit2.Response
import retrofit2.http.GET

/**
 * Interfaz que define las rutas exactas de la API que vamos a usar.
 * Es como un "menú" de opciones que le pedimos al servidor.
 */
interface MealEndpoints {
    
    /**
     * Pide una receta aleatoria al servidor.
     * @GET("random.php") indica que se añade eso a la URL base.
     */
    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealResponse>
}
