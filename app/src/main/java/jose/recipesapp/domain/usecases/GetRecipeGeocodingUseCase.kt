package jose.recipesapp.domain.usecases

import jose.recipesapp.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeGeocodingUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {

    operator fun invoke(area: String) =
        recipeRepository.getRecipeGeocoding(area)
}