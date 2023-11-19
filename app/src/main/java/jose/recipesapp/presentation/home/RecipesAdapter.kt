package jose.recipesapp.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jose.recipesapp.R
import jose.recipesapp.domain.model.Recipe

internal class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    private val recipes = mutableListOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_view_holder, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    fun setRecipes(recipes: List<Recipe>) {
        this.recipes.clear()
        this.recipes.addAll(recipes)
        notifyDataSetChanged()
    }

    fun filterRecipes(query: String) {
        val filteredRecipes = recipes.filter {
            it.name?.contains(query, true) == true || it.description?.contains(query, true) == true
        }
        setRecipes(filteredRecipes)
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: Recipe) {
            val nameTextView = itemView.findViewById<TextView>(R.id.recipeNameTextView)
            val areaTextView =
                itemView.findViewById<TextView>(R.id.recipeAreaTextView)
            val recipeImageView = itemView.findViewById<ImageView>(R.id.recipeImageView)
            val categoryTextView =
                itemView.findViewById<TextView>(R.id.recipeCategoryTextView)
            nameTextView.text = recipe.name
            areaTextView.text = recipe.area
            categoryTextView.text = recipe.category
            Glide.with(itemView.context).load(recipe.image).into(recipeImageView)
        }
    }
}

