package com.example.coding_challenge.presentation.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coding_challenge.domain.AcromineResponse

@BindingAdapter("variations")
fun setVariations(rv: RecyclerView, list: List<AcromineResponse.AcromineResponseItem.Lf.Var?>) {
    val variationRVAdapter = VariationRVAdapter()
    rv.adapter = variationRVAdapter
    rv.layoutManager = LinearLayoutManager(rv.context)
    variationRVAdapter.submitList(list)
}