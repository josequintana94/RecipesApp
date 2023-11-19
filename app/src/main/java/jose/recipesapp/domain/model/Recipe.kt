package jose.recipesapp.domain.model

import java.io.Serializable

data class Recipe(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
    val category: String? = null,
    val area: String? = null,
    val youtubeLink: String? = null,
    val sourceLink: String? = null,
) : Serializable