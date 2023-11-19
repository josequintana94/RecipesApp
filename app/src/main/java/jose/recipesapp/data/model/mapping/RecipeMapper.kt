package jose.recipesapp.data.model.mapping

import jose.recipesapp.data.model.dto.RecipeDTO
import jose.recipesapp.data.model.dto.RecipesDTO
import jose.recipesapp.domain.model.Recipe

fun RecipeDTO.toDomainModel(): Recipe {
    return Recipe(
        id = idMeal,
        name = strMeal,
        description = strInstructions,
        image = strMealThumb,
        category = strCategory,
        area = strArea,
        youtubeLink = strYoutube,
        sourceLink = strSource
    )
}

fun RecipesDTO.toDomainModel(): List<Recipe> {
    return meals.map { it.toDomainModel() }
}