package com.example.basicmaterial3.presentation.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicmaterial3.domain.AcromineResponse

@BindingAdapter("variations")
fun setVariations(rv: RecyclerView, list: List<AcromineResponse.AcromineResponseItem.Lf.Var?>) {
    val variationRVAdapter = VariationRVAdapter()
    rv.adapter = variationRVAdapter
    rv.layoutManager = LinearLayoutManager(rv.context)
    variationRVAdapter.submitList(list)
}