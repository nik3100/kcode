package com.kcode.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kcode.data.model.Data
import com.kcode.databinding.ItemHomeUserlistBinding
import javax.inject.Inject

class HomeUserListAdapter @Inject constructor() :
    PagingDataAdapter<Data, HomeUserListAdapter.DataViewHolder>(DataComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemHomeUserlistBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class DataViewHolder(private val binding: ItemHomeUserlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            binding.txtviewUserName.text = item.firstName
        }
    }

    object DataComparator : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Data, newItem: Data) =
            oldItem == newItem
    }
}