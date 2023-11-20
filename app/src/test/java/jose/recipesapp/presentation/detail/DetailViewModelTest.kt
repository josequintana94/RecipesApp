package jose.recipesapp.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.android.gms.maps.model.LatLng
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import jose.recipesapp.Mocks
import jose.recipesapp.domain.model.Recipe
import jose.recipesapp.domain.repository.RecipeRepository
import jose.recipesapp.domain.usecases.GetRecipeGeocodingUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var recipeRepository: RecipeRepository
    private lateinit var getRecipeGeocodingUseCase: GetRecipeGeocodingUseCase
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var mockRecipe: Recipe
    private lateinit var mockLatLng: LatLng

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockKAnnotations.init(this)
        recipeRepository = mockk()
        getRecipeGeocodingUseCase = GetRecipeGeocodingUseCase(recipeRepository)
        mockRecipe = Mocks.getMockRecipe()
        mockLatLng = Mocks.getMockLatLng()
        detailViewModel = DetailViewModel(getRecipeGeocodingUseCase)
    }

    @Test
    fun `should get recipe geocode data when onViewInit is called`() {
        givenGetRecipeGeocoding()

        whenGetRecipeGeocoding()

        thenGetRecipeGeocodingIsCalled()
    }

    private fun givenGetRecipeGeocoding() {
        val latLng = Mocks.getMockLatLng()
        every { recipeRepository.getRecipeGeocoding(mockRecipe.area.toString()) } returns Single.just(latLng)
    }

    private fun whenGetRecipeGeocoding() {
        detailViewModel.onViewInit(mockRecipe)
    }

    private fun thenGetRecipeGeocodingIsCalled() {
        verify { recipeRepository.getRecipeGeocoding(mockRecipe.area.toString()) }
        assert(detailViewModel.latLng.value == mockLatLng)
        assert(detailViewModel.isLoading.value == false)
    }
}