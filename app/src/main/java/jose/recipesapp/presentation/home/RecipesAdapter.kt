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

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(recipe: Recipe) {
            val nameTextView = itemView.findViewById<TextView>(R.id.recipeNameTextView)
            val descriptionTextView =
                itemView.findViewById<TextView>(R.id.recipeDescriptionTextView)
            val recipeImageView = itemView.findViewById<ImageView>(R.id.recipeImageView)

            nameTextView.text = recipe.name
            val recipeDescription = "${recipe.description?.take(100)} ..."
            descriptionTextView.text = recipeDescription
            Glide.with(itemView.context).load(recipe.image).into(recipeImageView)
        }
    }
}

