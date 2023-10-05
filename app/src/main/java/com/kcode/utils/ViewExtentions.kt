package com.kcode.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kcode.R

fun AppCompatImageView.setImage(context: Context, url: String) {
    Glide.with(context).load(url).placeholder(R.mipmap.ic_launcher).into(this)
}

fun AppCompatImageView.setImage(context: Context, id: Int) {
    Glide.with(context).load(id).placeholder(R.mipmap.ic_launcher).into(this)
}

fun AppCompatImageView.setRoundedImage(context: Context, id: Int, radius: Int = 24) {
    Glide.with(context).load(id).transform(RoundedCorners(radius))
        .placeholder(R.mipmap.ic_launcher).into(this)
}

fun String?.nullSafe(default: String = ""): String {
    if (this == null)
        return default
    else
        return this
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Activity.showToast(msg: String = "") {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(msg: String = "") {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}
