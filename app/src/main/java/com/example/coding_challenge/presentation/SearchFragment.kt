package com.example.coding_challenge.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coding_challenge.R
import com.example.coding_challenge.databinding.FragmentSearchBinding
import com.example.coding_challenge.domain.InputValidator
import com.example.coding_challenge.domain.InputValidatorHelper
import com.example.coding_challenge.presentation.adapters.LongformRVAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Annotated as AndroidEntryPoint so that its instance is created and handled by hilt
 */
@AndroidEntryPoint
class SearchFragment : Fragment() {

    /**
     * created instance of SearchViewModel by delegate function viewModels()
     */
    private val viewModel: SearchViewModel by viewModels()

    /**
     * lateinit variable declaration of FragmentSearchBinding
     */
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /**
         * using DataBindingUtil for inflating the layout file fragment_search by dataBinding
         */
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        /**
         * providing the lifecylceOwner to the binding reference for handling the dataBiding in the xml
         */
        binding.lifecycleOwner = viewLifecycleOwner
        /**
         * passing the SearchViewModel reference to the binding
         */
        binding.searchViewModel = viewModel
        /**
         * returning the root view to the onCreateView function
         */
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * enabling the keyboard in searchView by default
         */
        binding.searchView.isIconified = false
        /**
         * setting the layoutManager to the recyclerView present in SearchFragment
         */
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        /**
         * creating the instance of LongformRVAdapter for providing it to recyclerview
         */
        val longformRVAdapter = LongformRVAdapter()
        /**
         * passing the lingformRVAdapter reference to the recyclerview
         */
        binding.rv.adapter = longformRVAdapter

        /**
         * observing the api response received
         */
        viewModel.acromineLiveData.observe(viewLifecycleOwner) {
            it?.let { list ->
                longformRVAdapter.submitList(list.firstOrNull()?.lfs)
            }
        }
        /**
         * observing the value of searchView
         */
        viewModel.queryMutableLiveData.observe(viewLifecycleOwner) {
            when (val input = InputValidatorHelper().validateSearchedText(it)) {
                InputValidator.EmptyInput, InputValidator.SmallerTo3CharacterInput -> {
                    showSnackBar(getString(R.string.please_enter_minimum_3_characters))
                    longformRVAdapter.submitList(null)
                }
                is InputValidator.ValidInput -> {
                    lifecycleScope.launchWhenStarted {
                        viewModel.getAcromine(input.sf)
                    }
                }
            }
        }

        /**
         * setting the queryTextListener to the searchView that is declared in the viewModel
         */
        binding.searchView.setOnQueryTextListener(viewModel.onQueryTextChangeListener)

        /**
         * observing the error message mutableLiveData
         */
        viewModel.acromineErrorMutableLiveData.observe(viewLifecycleOwner) {
            it?.let { msg ->
                showSnackBar(msg)
                viewModel.acromineErrorMutableLiveData.postValue(null)
            }
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.searchView, message, Snackbar.LENGTH_LONG).show()
    }


}