package com.example.workaholic.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.*
import com.example.workaholic.R
import kotlinx.android.synthetic.main.activity_user_profile.*
import java.io.IOException

class GlideLoader (val context: Context){
    fun loadUserPicture(imageUri: Uri, imageView: ImageView)
    {
        try{
        Glide.
            with(context)
            .load(imageUri)
            .fitCenter()
            .placeholder(R.drawable.download)
            .into(imageView)
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
}