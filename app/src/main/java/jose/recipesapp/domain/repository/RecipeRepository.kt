package jose.recipesapp.domain.repository

import io.reactivex.Single
import jose.recipesapp.domain.model.Recipe

interface RecipeRepository {
    fun getRecipes(): Single<List<Recipe>>
}