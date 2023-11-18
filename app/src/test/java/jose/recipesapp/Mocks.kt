package jose.recipesapp

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

        fun getMockRecipeList(): List<Recipe> {
            return mockRecipeList
        }
    }
}