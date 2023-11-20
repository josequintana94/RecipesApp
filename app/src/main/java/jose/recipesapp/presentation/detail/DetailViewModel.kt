package jose.recipesapp.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jose.recipesapp.domain.model.Recipe
import jose.recipesapp.domain.usecases.GetRecipeGeocodingUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getRecipeGeocodingUseCase: GetRecipeGeocodingUseCase) :
    ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var latLng = MutableLiveData<LatLng>()

    init {
        isLoading.value = true
    }

    fun onViewInit(recipe: Recipe) {
        getRecipeGeocodingUseCase.invoke(recipe.area!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                latLng.value = it
                isLoading.value = false
            }, {
                it.printStackTrace()
                isLoading.value = false
            })
    }
}