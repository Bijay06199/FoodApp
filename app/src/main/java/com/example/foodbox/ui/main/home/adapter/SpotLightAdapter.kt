package com.example.foodbox.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbox.R
import com.example.foodbox.databinding.SpotlightLayoutBinding
import com.example.foodbox.ui.main.home.model.SpotLightModel

class SpotLightAdapter(private val listener:OnItemClickListener): RecyclerView.Adapter<SpotLightAdapter.ViewHolder>(){
    var itemList = ArrayList<SpotLightModel>()

    inner class ViewHolder(var mBinding: SpotlightLayoutBinding): RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding: SpotlightLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.spotlight_layout,parent,false)
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
        fun onClicked(position: Int,itemList: SpotLightModel)
    }

    fun addAll(itemList: ArrayList<SpotLightModel>){
        this.itemList= itemList
        notifyDataSetChanged()
    }

}