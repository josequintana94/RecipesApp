package jose.recipesapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import jose.recipesapp.R

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var recipesReciclerView: RecyclerView? = null
    private var recipesAdapter: RecipesAdapter? = null
    private var recipesSearchView: SearchView? = null
    private var loadingLayout: LinearLayout? = null

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
        loadingLayout = view.findViewById(R.id.loadingLayout)
        recipesReciclerView = view.findViewById(R.id.recipesRecyclerView)
        recipesAdapter = RecipesAdapter()
        recipesReciclerView?.adapter = recipesAdapter

        recipesSearchView = view.findViewById(R.id.recipesSearchView)
        recipesSearchView?.setOnClickListener {
            recipesSearchView?.isIconified = false
        }

        recipesSearchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                println("onQueryTextSubmit: $query")
                if (query.isNullOrEmpty()) {
                    recipesAdapter?.setRecipes(viewModel.recipes.value ?: emptyList())
                } else {
                    recipesAdapter?.filterRecipes(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                println("onQueryTextChange: $newText")
                if (newText.isNullOrEmpty()) {
                    recipesAdapter?.setRecipes(viewModel.recipes.value ?: emptyList())
                } else {
                    recipesAdapter?.filterRecipes(newText)
                }
                return true
            }
        })

        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) {
                println("isLoading: $it")
                if (it) {
                    recipesReciclerView?.visibility = View.GONE
                    loadingLayout?.visibility = View.VISIBLE
                } else {
                    recipesReciclerView?.visibility = View.VISIBLE
                    loadingLayout?.visibility = View.GONE
                }
            }

            recipes.observe(viewLifecycleOwner) {
                println("recipes: ${it.size}")
                recipesAdapter?.setRecipes(it)
            }
        }
    }
}