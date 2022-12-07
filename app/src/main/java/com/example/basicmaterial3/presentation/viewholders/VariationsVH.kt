package com.example.basicmaterial3.presentation.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.basicmaterial3.databinding.VariationsItemsBinding
import com.example.basicmaterial3.domain.AcromineResponse

class VariationsVH(private val variationsItemsBinding: VariationsItemsBinding) :
    RecyclerView.ViewHolder(variationsItemsBinding.root) {
    fun bind(varitaion: AcromineResponse.AcromineResponseItem.Lf.Var?) {
        variationsItemsBinding.varItem = varitaion
        variationsItemsBinding.executePendingBindings()
    }
}