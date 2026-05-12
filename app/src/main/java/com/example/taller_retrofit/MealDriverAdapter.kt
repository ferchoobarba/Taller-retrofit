package com.example.taller_retrofit

/**
 * Esta clase actúa como un "puente" o intermediario.
 * Su objetivo es conectar la interfaz visual (MainActivity) con la lógica de datos (MealServices).
 */
class MealDriverAdapter {

    // Instancia del servicio que sabe cómo hablar con internet
    private val mealServices: MealServices = MealServices()

    /**
     * Función que llama la pantalla principal para pedir una nueva receta.
     * @param load Qué hacer cuando lleguen los datos de la receta.
     * @param error Qué hacer si la carga falla.
     */
    fun loadRandomMeal(
        load: (List<Meal>) -> Unit,
        error: () -> Unit
    ) {
        // Le dice al servicio: "Por favor, busca una receta y avísame cuando termines"
        mealServices.getRandomMeal(
            success = { data -> load(data) },
            catchError = { error() }
        )
    }
}
