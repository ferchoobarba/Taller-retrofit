package com.example.myapplicationroom.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationroom.network.Meal
import com.example.myapplicationroom.network.RetrofitClient
import kotlinx.coroutines.launch

sealed class MealState {
    object Idle : MealState()
    object Loading : MealState()
    data class Success(val meal: Meal) : MealState()
    data class Error(val message: String) : MealState()
}

class MealViewModel : ViewModel() {
    private val _state = mutableStateOf<MealState>(MealState.Idle)
    val state: State<MealState> = _state

    fun getRandomMeal() {
        viewModelScope.launch {
            _state.value = MealState.Loading
            try {
                val response = RetrofitClient.instance.getRandomMeal()
                val meal = response.meals?.firstOrNull()
                if (meal != null) {
                    _state.value = MealState.Success(meal)
                } else {
                    _state.value = MealState.Error("No se encontró ninguna receta")
                }
            } catch (e: Exception) {
                _state.value = MealState.Error("Error al cargar la receta: ${e.localizedMessage}")
            }
        }
    }
}
