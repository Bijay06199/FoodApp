package com.example.foodbox.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbox.R
import com.example.foodbox.databinding.TopPicksLayoutBinding
import com.example.foodbox.ui.main.home.model.TopPicksModel

class TopPicksAdapter(private val listener:OnItemClickListener): RecyclerView.Adapter<TopPicksAdapter.ViewHolder>(){
    var itemList = ArrayList<TopPicksModel>()

    inner class ViewHolder(var mBinding: TopPicksLayoutBinding): RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding: TopPicksLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.top_picks_layout,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mBinding.model= this.itemList[position]
        holder.mBinding.root.setOnClickListener{
            listener.onClicked(holder.adapterPosition,itemList[holder.adapterPosition])
        }
    }

    interface OnItemClickListener{
        fun onClicked(position: Int,itemList:TopPicksModel)
    }

    fun addAll(itemList: ArrayList<TopPicksModel>){
        this.itemList= itemList
        notifyDataSetChanged()
    }

}