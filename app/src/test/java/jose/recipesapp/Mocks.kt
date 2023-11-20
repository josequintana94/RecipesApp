package jose.recipesapp

import com.google.android.gms.maps.model.LatLng
import jose.recipesapp.domain.model.Recipe

class Mocks {
    companion object {
        private val mockRecipeList = listOf(
            Recipe(
                "1",
                "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
                "Beef and Mustard Pie",
                "beef.png"
            )
        )

        private val mockArea = "England"

        private val mockLatLng = LatLng(52.3555177, -1.1743197)

        private val recipe = Recipe(
            "1",
            "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
            "Beef and Mustard Pie",
            "beef.png",
            "Dinner",
            mockArea,
        )

        fun getMockRecipeList(): List<Recipe> {
            return mockRecipeList
        }

        fun getMockArea(): String {
            return mockArea
        }

        fun getMockLatLng(): LatLng {
            return mockLatLng
        }

        fun getMockRecipe(): Recipe {
            return recipe
        }
    }
}