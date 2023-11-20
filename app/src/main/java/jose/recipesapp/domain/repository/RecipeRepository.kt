package jose.recipesapp.domain.repository

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single
import jose.recipesapp.domain.model.Recipe

interface RecipeRepository {
    fun getRecipes(): Single<List<Recipe>>

    fun getRecipeGeocoding(area: String): Single<LatLng>
}