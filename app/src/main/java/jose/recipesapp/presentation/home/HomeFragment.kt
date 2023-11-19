package jose.recipesapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import jose.recipesapp.R

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var recipesReciclerView: RecyclerView? = null
    private var recipesAdapter: RecipesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.loadRecipes()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        recipesReciclerView = view.findViewById(R.id.recipesRecyclerView)
        recipesAdapter = RecipesAdapter()
        recipesReciclerView?.adapter = recipesAdapter

        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) {
                println("isLoading: $it")
            }

            recipes.observe(viewLifecycleOwner) {
                println("recipes: ${it.size}")
                recipesAdapter?.setRecipes(it)
            }
        }
    }
}