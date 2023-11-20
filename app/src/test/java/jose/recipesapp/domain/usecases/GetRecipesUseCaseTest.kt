package jose.recipesapp.domain.usecases

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import jose.recipesapp.Mocks
import jose.recipesapp.domain.model.Recipe
import jose.recipesapp.domain.repository.RecipeRepository
import org.junit.Before
import org.junit.Test

class GetRecipesUseCaseTest {
    private lateinit var recipeRepository: RecipeRepository
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        recipeRepository = mockk()
        getRecipesUseCase = GetRecipesUseCase(recipeRepository)
    }

    @Test
    fun `when getRecipesUseCase is called`() {
        givenRecipeRepository()

        whenGetRecipesUseCase()

        thenGetRecipesIsCalled()
    }

    private fun givenRecipeRepository() {
        val singleRecipes: Single<List<Recipe>> = Single.just(Mocks.getMockRecipeList())

        every { recipeRepository.getRecipes() } returns singleRecipes
    }

    private fun whenGetRecipesUseCase() {
        getRecipesUseCase.invoke()
    }

    private fun thenGetRecipesIsCalled() {
        verify { recipeRepository.getRecipes() }
    }
}