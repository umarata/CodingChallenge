package com.example.basicmaterial3.presentation.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.basicmaterial3.databinding.LongformItemsBinding
import com.example.basicmaterial3.domain.AcromineResponse


class LongformVH(private val longformItemsBinding: LongformItemsBinding) :
    RecyclerView.ViewHolder(longformItemsBinding.root) {
    fun bind(lf: AcromineResponse.AcromineResponseItem.Lf?) {
        longformItemsBinding.longform = lf
        longformItemsBinding.executePendingBindings()
    }
}