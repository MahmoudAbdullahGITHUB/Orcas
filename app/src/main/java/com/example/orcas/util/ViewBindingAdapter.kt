package com.example.orcas.util


import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.TextViewCompat

import androidx.databinding.BindingAdapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.google.android.material.card.MaterialCardView
import com.example.orcas.R
import com.example.orcas.util.extensions.loadSvg

object ViewBindingAdapter {

    @SuppressLint("CheckResult")
    @JvmStatic
    @BindingAdapter("bind_image", "image_type", "place_holder", requireAll = false)
    fun bindImage(
        image: AppCompatImageView,
        path: Any? = null,
        imageType: ImageType = ImageType.PURE_PATH,
        placeHolderResource: Any? = null
    ) {
        path?.let {
            val imagePath = when (imageType) {
                ImageType.COUNTRY -> "https://www.countryflags.io/$path/shiny/64.png"
                ImageType.PURE_PATH -> path
                ImageType.CATEGORY -> {
                    path
//                    if (SharedCodeConfiguration.isDebug) {
//                        "${SharedCodeConfiguration.url}CMS2/$path"
//                    } else {
//                        "${SharedCodeConfiguration.url}$path"
//                    }
                }
            }

            if (imagePath.toString().contains(".svg")) {
                image.loadSvg(imagePath.toString())
            } else {
                Glide.with(image).load(imagePath).diskCacheStrategy(DiskCacheStrategy.ALL).apply {
                    if (placeHolderResource != null) {
                        val drawable = placeHolderResource as Drawable
                        // @SuppressLint("CheckResult")
                        placeholder(drawable)
                        error(drawable)
                    }
                    error(R.drawable.smart_van_img)
                    placeholder(R.drawable.smart_van_img)
                }.into(image)
            }
        }
    }


    @JvmStatic
    @BindingAdapter("cardBackground")
    fun changeBackground(view: MaterialCardView, isSelected: Boolean) {
        if (isSelected)
            view.setBackgroundColor(view.context.resources.getColor(R.color.app_color))
        else
            view.setBackgroundColor(view.context.resources.getColor(R.color.light_app_color))
    }

    @JvmStatic
    @BindingAdapter("textColorBinding")
    fun changeTextColor(view: TextView, isSelected: Boolean) {
        if (isSelected)
            view.setTextColor(view.context.resources.getColor(R.color.white))
        else
            view.setTextColor(view.context.resources.getColor(R.color.black))
    }

    @JvmStatic
    @BindingAdapter("imageTintBinding")
    fun changeImageTint(view: AppCompatImageView, isSelected: Boolean) {
        if (isSelected)
            view.imageTintList =
                ColorStateList.valueOf(view.context.resources.getColor(R.color.white))
        else
            view.imageTintList =
                ColorStateList.valueOf(view.context.resources.getColor(R.color.black))
    }
}