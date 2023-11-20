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

        private val mockArea = "Argentina"

        private val mockLatLng = LatLng(-34.603722, -58.381592)

        fun getMockRecipeList(): List<Recipe> {
            return mockRecipeList
        }

        fun getMockArea(): String {
            return mockArea
        }

        fun getMockLatLng(): LatLng {
            return mockLatLng
        }
    }
}