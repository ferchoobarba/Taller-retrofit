package com.example.taller_retrofit

import com.google.gson.annotations.SerializedName

// General Response for Meals
data class MealResponse(
    @SerializedName("meals") var meals: List<Meal>?
)

data class Meal(
    var idMeal: String,
    var strMeal: String,
    var strCategory: String,
    var strMealThumb: String,
    var strInstructions: String? = null,
    var strIngredient1: String? = null,
    var strIngredient2: String? = null,
    var strIngredient3: String? = null,
    var strIngredient4: String? = null,
    var strIngredient5: String? = null,
    var strIngredient6: String? = null,
    var strIngredient7: String? = null,
    var strIngredient8: String? = null,
    var strIngredient9: String? = null,
    var strIngredient10: String? = null,
    var strIngredient11: String? = null,
    var strIngredient12: String? = null,
    var strIngredient13: String? = null,
    var strIngredient14: String? = null,
    var strIngredient15: String? = null,
    var strIngredient16: String? = null,
    var strIngredient17: String? = null,
    var strIngredient18: String? = null,
    var strIngredient19: String? = null,
    var strIngredient20: String? = null,
    var strMeasure1: String? = null,
    var strMeasure2: String? = null,
    var strMeasure3: String? = null,
    var strMeasure4: String? = null,
    var strMeasure5: String? = null,
    var strMeasure6: String? = null,
    var strMeasure7: String? = null,
    var strMeasure8: String? = null,
    var strMeasure9: String? = null,
    var strMeasure10: String? = null,
    var strMeasure11: String? = null,
    var strMeasure12: String? = null,
    var strMeasure13: String? = null,
    var strMeasure14: String? = null,
    var strMeasure15: String? = null,
    var strMeasure16: String? = null,
    var strMeasure17: String? = null,
    var strMeasure18: String? = null,
    var strMeasure19: String? = null,
    var strMeasure20: String? = null
) {
    fun getIngredients(): List<Pair<String, String>> {
        return listOfNotNull(
            if (!strIngredient1.isNullOrBlank()) strIngredient1!! to (strMeasure1 ?: "") else null,
            if (!strIngredient2.isNullOrBlank()) strIngredient2!! to (strMeasure2 ?: "") else null,
            if (!strIngredient3.isNullOrBlank()) strIngredient3!! to (strMeasure3 ?: "") else null,
            if (!strIngredient4.isNullOrBlank()) strIngredient4!! to (strMeasure4 ?: "") else null,
            if (!strIngredient5.isNullOrBlank()) strIngredient5!! to (strMeasure5 ?: "") else null,
            if (!strIngredient6.isNullOrBlank()) strIngredient6!! to (strMeasure6 ?: "") else null,
            if (!strIngredient7.isNullOrBlank()) strIngredient7!! to (strMeasure7 ?: "") else null,
            if (!strIngredient8.isNullOrBlank()) strIngredient8!! to (strMeasure8 ?: "") else null,
            if (!strIngredient9.isNullOrBlank()) strIngredient9!! to (strMeasure9 ?: "") else null,
            if (!strIngredient10.isNullOrBlank()) strIngredient10!! to (strMeasure10 ?: "") else null,
            if (!strIngredient11.isNullOrBlank()) strIngredient11!! to (strMeasure11 ?: "") else null,
            if (!strIngredient12.isNullOrBlank()) strIngredient12!! to (strMeasure12 ?: "") else null,
            if (!strIngredient13.isNullOrBlank()) strIngredient13!! to (strMeasure13 ?: "") else null,
            if (!strIngredient14.isNullOrBlank()) strIngredient14!! to (strMeasure14 ?: "") else null,
            if (!strIngredient15.isNullOrBlank()) strIngredient15!! to (strMeasure15 ?: "") else null,
            if (!strIngredient16.isNullOrBlank()) strIngredient16!! to (strMeasure16 ?: "") else null,
            if (!strIngredient17.isNullOrBlank()) strIngredient17!! to (strMeasure17 ?: "") else null,
            if (!strIngredient18.isNullOrBlank()) strIngredient18!! to (strMeasure18 ?: "") else null,
            if (!strIngredient19.isNullOrBlank()) strIngredient19!! to (strMeasure19 ?: "") else null,
            if (!strIngredient20.isNullOrBlank()) strIngredient20!! to (strMeasure20 ?: "") else null
        )
    }
}
