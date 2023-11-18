package jose.recipesapp.domain.usecases

import io.reactivex.Single
import jose.recipesapp.domain.model.Recipe
import jose.recipesapp.domain.repository.RecipeRepository
import jose.recipesapp.domain.usecases.base.SingleUseCase
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) : SingleUseCase<List<Recipe>>() {
    override fun buildUseCaseSingle(): Single<List<Recipe>> {
        return recipeRepository.getRecipes()
    }
}