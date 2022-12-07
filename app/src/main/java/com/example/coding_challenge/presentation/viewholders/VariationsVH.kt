package com.example.coding_challenge.presentation.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.coding_challenge.databinding.VariationsItemsBinding
import com.example.coding_challenge.domain.AcromineResponse

class VariationsVH(private val variationsItemsBinding: VariationsItemsBinding) :
    RecyclerView.ViewHolder(variationsItemsBinding.root) {
    fun bind(varitaion: AcromineResponse.AcromineResponseItem.Lf.Var?) {
        variationsItemsBinding.varItem = varitaion
        variationsItemsBinding.executePendingBindings()
    }
}