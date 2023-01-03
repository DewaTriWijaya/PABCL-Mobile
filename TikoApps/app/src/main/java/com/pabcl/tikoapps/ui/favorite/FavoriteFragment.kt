package com.pabcl.tikoapps.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.pabcl.tikoapps.databinding.FragmentFavoriteBinding
import com.pabcl.tikoapps.ui.ViewModelFactory
import com.pabcl.tikoapps.ui.home.HomeAdapter
import com.pabcl.tikoapps.ui.home.HomeViewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        val viewModel: HomeViewModel by viewModels {
            factory
        }

        val productsAdapter = HomeAdapter { products ->
            if (products.isFavorite){
                viewModel.deleteProducts(products)
            } else {
                viewModel.saveProducts(products)
            }
        }

        viewModel.getFavorite().observe(viewLifecycleOwner) {
            productsAdapter.submitList(it)
        }

        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter = productsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}