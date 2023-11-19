package jose.recipesapp.data.source

import io.reactivex.Single
import jose.recipesapp.data.model.dto.RecipesDTO
import retrofit2.http.GET

interface RetrofitService {
    @GET("recipes")
    fun getRecipes(): Single<RecipesDTO>
}
