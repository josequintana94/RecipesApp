package jose.recipesapp.domain.usecases

import jose.recipesapp.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    operator fun invoke() =
        recipeRepository.getRecipes()
}