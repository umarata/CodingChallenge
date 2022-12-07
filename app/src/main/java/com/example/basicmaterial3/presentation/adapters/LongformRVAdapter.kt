package com.example.basicmaterial3.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicmaterial3.databinding.LongformItemsBinding
import com.example.basicmaterial3.domain.AcromineResponse
import com.example.basicmaterial3.presentation.viewholders.LongformVH


class LongformRVAdapter : RecyclerView.Adapter<LongformVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LongformVH {
        val longformItemsBinding =
            LongformItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LongformVH(longformItemsBinding)
    }

    override fun onBindViewHolder(holder: LongformVH, position: Int) {
        holder.bind(mDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    val DIFF_CALLBACK =
        object : DiffUtil.ItemCallback<AcromineResponse.AcromineResponseItem.Lf>() {

            override fun areItemsTheSame(
                oldItem: AcromineResponse.AcromineResponseItem.Lf,
                newItem: AcromineResponse.AcromineResponseItem.Lf
            ): Boolean {
                return oldItem.freq == newItem.freq
            }

            override fun areContentsTheSame(
                oldItem: AcromineResponse.AcromineResponseItem.Lf,
                newItem: AcromineResponse.AcromineResponseItem.Lf
            ): Boolean {
                return oldItem == newItem
            }

        }
    private val mDiffer: AsyncListDiffer<AcromineResponse.AcromineResponseItem.Lf> =
        AsyncListDiffer(this, DIFF_CALLBACK)


    fun submitList(variationsList: List<AcromineResponse.AcromineResponseItem.Lf?>?) {
        mDiffer.submitList(variationsList)
    }
}