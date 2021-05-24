package com.example.foodbox.ui.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbox.R
import com.example.foodbox.databinding.BannerLayoutBinding
import com.example.foodbox.ui.main.home.model.BannerModel

class BannerAdapter( var context: Context): RecyclerView.Adapter<BannerAdapter.BannerViewHolder>(){
    var itemList = ArrayList<BannerModel>()

    inner class BannerViewHolder(var mBinding: BannerLayoutBinding): RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        var binding:BannerLayoutBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.banner_layout,parent,false)
        return BannerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.mBinding.model= this.itemList[position]
    }

    fun addAll(itemList: ArrayList<BannerModel>){
        this.itemList= itemList
        notifyDataSetChanged()
    }

}