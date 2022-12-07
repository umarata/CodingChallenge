package com.example.coding_challenge.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coding_challenge.databinding.VariationsItemsBinding
import com.example.coding_challenge.domain.AcromineResponse
import com.example.coding_challenge.presentation.viewholders.VariationsVH


class VariationRVAdapter : RecyclerView.Adapter<VariationsVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariationsVH {
        val variationsItemsBinding =
            VariationsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VariationsVH(variationsItemsBinding)
    }

    override fun onBindViewHolder(holder: VariationsVH, position: Int) {
        holder.bind(mDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    val DIFF_CALLBACK =
        object : DiffUtil.ItemCallback<AcromineResponse.AcromineResponseItem.Lf.Var?>() {

            override fun areItemsTheSame(
                oldItem: AcromineResponse.AcromineResponseItem.Lf.Var,
                newItem: AcromineResponse.AcromineResponseItem.Lf.Var
            ): Boolean {
                return oldItem.freq == newItem.freq
            }

            override fun areContentsTheSame(
                oldItem: AcromineResponse.AcromineResponseItem.Lf.Var,
                newItem: AcromineResponse.AcromineResponseItem.Lf.Var
            ): Boolean {
                return oldItem == newItem
            }

        }
    private val mDiffer: AsyncListDiffer<AcromineResponse.AcromineResponseItem.Lf.Var?> =
        AsyncListDiffer<AcromineResponse.AcromineResponseItem.Lf.Var?>(this, DIFF_CALLBACK)


    fun submitList(variationsList: List<AcromineResponse.AcromineResponseItem.Lf.Var?>?) {
        mDiffer.submitList(variationsList)
    }
}