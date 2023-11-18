package jose.recipesapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jose.recipesapp.data.repository.RecipeRepositoryImpl
import jose.recipesapp.domain.usecases.GetRecipesUseCase

@Module
@InstallIn(SingletonComponent::class)
class RecipesModule {
    @Provides
    fun providesRecipeRepository() = RecipeRepositoryImpl()

    @Provides
    fun provideGetRecipesUseCase(repository: RecipeRepositoryImpl) = GetRecipesUseCase(repository)
}