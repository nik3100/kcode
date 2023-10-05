package com.kcode.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kcode.data.model.ResponseModel
import com.kcode.databinding.ItemWelcomeBinding


class WelcomeViewPagerAdapter(private val welcomeList: ArrayList<ResponseModel>) :
    RecyclerView.Adapter<WelcomeViewPagerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemWelcomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = welcomeList[position]
        holder.bind(data)

    }

    override fun getItemCount(): Int = welcomeList.size

    inner class MyViewHolder(private val binding: ItemWelcomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResponseModel) {
        }
    }

    fun submitData(list: ArrayList<ResponseModel>) {
        welcomeList.addAll(list)
        notifyDataSetChanged()
    }

}