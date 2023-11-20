package jose.recipesapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jose.recipesapp.domain.model.Recipe
import jose.recipesapp.domain.usecases.GetRecipesUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getRecipesUseCase: GetRecipesUseCase) :
    ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val recipes = MutableLiveData<List<Recipe>>()

    init {
        isLoading.value = true
    }

    fun loadRecipes() {
        getRecipesUseCase.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                recipes.value = it
                isLoading.value = false
            }, {
                it.printStackTrace()
                isLoading.value = false
            })
    }
}