package jose.recipesapp.data.repository

import io.reactivex.Single
import jose.recipesapp.domain.model.Recipe
import jose.recipesapp.domain.repository.RecipeRepository

class RecipeRepositoryImpl : RecipeRepository {
    override fun getRecipes(): Single<List<Recipe>> {
        //TODO: implement API call
        return Single.just(
            listOf(
                Recipe(
                    "1",
                    "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
                    "Beef and Mustard Pie",
                    "beef.png"
                )
            )
        )
    }
}