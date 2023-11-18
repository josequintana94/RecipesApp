package jose.recipesapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import jose.recipesapp.Mocks
import jose.recipesapp.domain.model.Recipe
import jose.recipesapp.domain.repository.RecipeRepository
import jose.recipesapp.domain.usecases.GetRecipesUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var recipeRepository: RecipeRepository
    private lateinit var getRecipesUseCase: GetRecipesUseCase
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        recipeRepository = mockk()
        getRecipesUseCase = GetRecipesUseCase(recipeRepository)
        homeViewModel = HomeViewModel(getRecipesUseCase)
    }

    @Test
    fun `when loadRecipes is called`() {
        givenLoadRecipes()

        whenLoadRecipes()

        thenLoadRecipesIsCalled()
    }

    private fun givenLoadRecipes() {
        val singleRecipes: Single<List<Recipe>> = Single.just(Mocks.getMockRecipeList())

        every { recipeRepository.getRecipes() } returns singleRecipes
    }

    private fun whenLoadRecipes() {
        homeViewModel.loadRecipes()
    }

    private fun thenLoadRecipesIsCalled() {
        verify { recipeRepository.getRecipes() }
    }
}