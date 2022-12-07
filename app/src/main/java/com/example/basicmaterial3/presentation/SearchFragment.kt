package com.example.basicmaterial3.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicmaterial3.R
import com.example.basicmaterial3.databinding.FragmentSearchBinding
import com.example.basicmaterial3.presentation.adapters.LongformRVAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.isIconified = false
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        val longformRVAdapter = LongformRVAdapter()
        binding.rv.adapter = longformRVAdapter

        viewModel.acromineLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                longformRVAdapter.submitList(it.firstOrNull()?.lfs)
            }
        }

        viewModel.queryMutableLiveData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                longformRVAdapter.submitList(null)
            } else {
                if (it.length >= 3) {
                    lifecycleScope.launchWhenStarted {
                        viewModel.getAcromine(it)
                    }
                } else //if (it.isNotEmpty() )
                {
                    Snackbar.make(
                        binding.searchView,
                        getString(R.string.please_enter_minimum_3_characters),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.searchView.setOnQueryTextListener(viewModel.onQueryTextChangeListener)

        viewModel.acromineErrorMutableLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                Snackbar.make(binding.searchView, it, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}