package com.example.foodbox.utils.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingUtils {

    @BindingAdapter("intImageSrc")
    @JvmStatic
    fun setImageSrc(imageView: ImageView, imageSrc:Int){
        imageView.setImageResource(imageSrc)
    }
}