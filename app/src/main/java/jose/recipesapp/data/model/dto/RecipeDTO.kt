package jose.recipesapp.data.model.dto

import com.google.gson.annotations.SerializedName

data class RecipesDTO(
    @SerializedName("meals")
    val meals: List<RecipeDTO>
)

data class RecipeDTO(
    val idMeal: String? = null,
    val strMeal: String? = null,
    val strCategory: String? = null,
    val strArea: String? = null,
    val strInstructions: String? = null,
    val strMealThumb: String? = null,
    val strYoutube: String? = null,
    val strSource: String? = null,
)