package com.example.pager.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun loadImg(context: Context, imgUrl: String, imageView: ImageView){
    Glide.with(context).load(imgUrl).into(imageView)
}