package jose.recipesapp.domain.usecases

import com.google.android.gms.maps.model.LatLng
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import jose.recipesapp.Mocks
import jose.recipesapp.domain.repository.RecipeRepository
import org.junit.Before
import org.junit.Test

class GetRecipeGeocodingUseCaseTest {
    private lateinit var recipeRepository: RecipeRepository
    private lateinit var getRecipeGeocodingUseCase: GetRecipeGeocodingUseCase

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        recipeRepository = mockk()
        getRecipeGeocodingUseCase = GetRecipeGeocodingUseCase(recipeRepository)
    }

    @Test
    fun `when getRecipeGeocodingUseCase is called`() {
        givenRecipeRepository()

        whenGetRecipeGeocodingUseCase()

        thenGetRecipeGeocodingIsCalled()
    }

    private fun givenRecipeRepository() {
        val singleLatLng: Single<LatLng> = Single.just(Mocks.getMockLatLng())
        coEvery { recipeRepository.getRecipeGeocoding(Mocks.getMockArea()) } returns singleLatLng
    }

    private fun whenGetRecipeGeocodingUseCase() {
        getRecipeGeocodingUseCase.invoke(Mocks.getMockArea())
    }

    private fun thenGetRecipeGeocodingIsCalled() {
        verify { recipeRepository.getRecipeGeocoding(Mocks.getMockArea()) }
    }
}