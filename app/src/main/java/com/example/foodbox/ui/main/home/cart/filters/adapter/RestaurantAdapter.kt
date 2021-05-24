package com.example.foodbox.ui.main.home.cart.filters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodbox.R
import com.example.foodbox.databinding.RecyclerviewRestaurantsBinding
import com.example.foodbox.ui.main.home.cart.filters.model.RestaurantModel

class RestaurantAdapter(private val listener:OnItemClickListener): RecyclerView.Adapter<RestaurantAdapter.ViewHolder>(){
    var itemList = ArrayList<RestaurantModel>()

    inner class ViewHolder(var mBinding: RecyclerviewRestaurantsBinding): RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding: RecyclerviewRestaurantsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.recyclerview_restaurants,parent,false)
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
        fun onClicked(position: Int,itemList: RestaurantModel)
    }

    fun addAll(itemList: ArrayList<RestaurantModel>){
        this.itemList= itemList
        notifyDataSetChanged()
    }

}