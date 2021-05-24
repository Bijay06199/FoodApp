package com.example.foodbox.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbox.R
import com.example.foodbox.databinding.DietTypeLayoutBinding
import com.example.foodbox.databinding.PopularLayoutBinding
import com.example.foodbox.ui.main.home.model.DietTypeModel

class PopularAdapter: RecyclerView.Adapter<PopularAdapter.BannerViewHolder>(){
    var itemList = ArrayList<DietTypeModel>()

    inner class BannerViewHolder(var mBinding: PopularLayoutBinding): RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        var binding: PopularLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.popular_layout,parent,false)
        return BannerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.mBinding.model= this.itemList[position]
    }

    fun addAll(itemList: ArrayList<DietTypeModel>){
        this.itemList= itemList
        notifyDataSetChanged()
    }

}