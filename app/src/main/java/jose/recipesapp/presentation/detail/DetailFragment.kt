package jose.recipesapp.presentation.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import jose.recipesapp.R

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val recipeImageView = view.findViewById<ImageView>(R.id.recipeImageView)
        val recipeNameTextView = view.findViewById<TextView>(R.id.recipeNameTextView)
        val descriptionTextView = view.findViewById<TextView>(R.id.recipeDescriptionTextView)
        val recipeCategoryTextView = view.findViewById<TextView>(R.id.recipeCategoryTextView)
        val recipeAreaTextView = view.findViewById<TextView>(R.id.recipeAreaTextView)
        val recipeYoutubeTextView = view.findViewById<TextView>(R.id.recipeYoutubeLinkTextView)
        val recipeSourceTextView = view.findViewById<TextView>(R.id.recipeSourceLinkTextView)
        val backArrowImageView = view.findViewById<ImageView>(R.id.backArrowImageView)
        val goToLocationButton = view.findViewById<TextView>(R.id.goToLocationButton)

        val recipe = args.recipe
        viewModel.onViewInit(recipe)

        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) {
                it?.let { visibility ->
                    view.findViewById<ProgressBar>(R.id.recipeLoadingProgressBar).visibility =
                        if (visibility) View.VISIBLE else View.GONE
                    view.findViewById<ScrollView>(R.id.layoutScrollView).visibility =
                        if (visibility) View.GONE else View.VISIBLE
                }
            }
            latLng.observe(viewLifecycleOwner) {
                it?.let { latLng ->
                    goToLocationButton.setOnClickListener {
                        val action =
                            DetailFragmentDirections.actionDetailFragmentToMapsFragment(latLng)
                        findNavController().navigate(action)
                    }
                }
            }
        }

        backArrowImageView.setOnClickListener {
            findNavController().popBackStack()
        }
        recipeNameTextView.text = recipe.name
        descriptionTextView.text = recipe.description
        recipeCategoryTextView.text = recipe.category
        recipeAreaTextView.text = recipe.area
        recipeYoutubeTextView.setOnClickListener {
            val url = recipe.youtubeLink
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        recipeSourceTextView.setOnClickListener {
            val url = recipe.sourceLink
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        Glide.with(recipeImageView)
            .load(recipe.image)
            .into(recipeImageView)
    }
}