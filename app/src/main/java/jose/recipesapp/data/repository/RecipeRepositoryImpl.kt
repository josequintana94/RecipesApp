package jose.recipesapp.data.repository

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import jose.recipesapp.data.source.RetrofitService
import jose.recipesapp.domain.model.Recipe
import jose.recipesapp.domain.repository.RecipeRepository
import javax.inject.Inject
import jose.recipesapp.data.model.mapping.toDomainModel
import jose.recipesapp.data.source.GeolocationProvider

class RecipeRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
    private val geolocationProvider: GeolocationProvider
) :
    RecipeRepository {
    override fun getRecipes(): Single<List<Recipe>> {
        val recipes = retrofitService.getRecipes()
        return recipes.map {
            it.toDomainModel()
        }
    }

    override fun getRecipeGeocoding(area: String): Single<LatLng> {
        return geolocationProvider.getGeocoding(area)
    }
}