package com.example.taller_retrofit

import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Clase base que configura la conexión a internet.
 * Todas las clases de "Servicios" heredan de aquí para usar Retrofit.
 */
abstract class BaseServices : ViewModel() {

    // La dirección principal de la API de recetas
    private val URL: String = "https://www.themealdb.com/api/json/v1/1/"

    /**
     * Configura y devuelve una instancia de Retrofit.
     * Retrofit es la librería que se encarga de convertir datos de internet (JSON) 
     * en objetos que Kotlin pueda entender.
     */
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(this.URL)
            .addConverterFactory(GsonConverterFactory.create()) // Convierte JSON a objetos automáticamente
            .build()
    }
}
