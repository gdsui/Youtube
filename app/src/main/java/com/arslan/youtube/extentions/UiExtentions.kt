package com.arslan.youtube.extentions

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.arslan.youtube.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun ImageView.loadImage (imageURL : String?, radius : Int){
    Glide.with(this.context)
        .load(imageURL)
        .placeholder(R.drawable.video_back)
        .transform(CenterCrop(), RoundedCorners(radius))
        .into(this)
}
fun Context.showToast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}

fun logger(name : String,message: String){
    Log.d(name,message)
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}