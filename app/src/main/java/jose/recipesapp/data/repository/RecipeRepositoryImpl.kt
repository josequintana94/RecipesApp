package jose.recipesapp.data.repository

import io.reactivex.Single
import jose.recipesapp.data.source.RetrofitService
import jose.recipesapp.domain.model.Recipe
import jose.recipesapp.domain.repository.RecipeRepository
import javax.inject.Inject
import jose.recipesapp.data.model.mapping.toDomainModel

class RecipeRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService) :
    RecipeRepository {
    override fun getRecipes(): Single<List<Recipe>> {
        val recipes = retrofitService.getRecipes()
        return recipes.map {
            it.toDomainModel()
        }
    }
}