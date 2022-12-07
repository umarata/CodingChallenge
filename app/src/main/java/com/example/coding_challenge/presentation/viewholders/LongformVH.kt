package com.example.coding_challenge.presentation.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.coding_challenge.databinding.LongformItemsBinding
import com.example.coding_challenge.domain.AcromineResponse


class LongformVH(private val longformItemsBinding: LongformItemsBinding) :
    RecyclerView.ViewHolder(longformItemsBinding.root) {
    fun bind(lf: AcromineResponse.AcromineResponseItem.Lf?) {
        longformItemsBinding.longform = lf
        longformItemsBinding.executePendingBindings()
    }
}