package com.tsp.learn.android.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import java.util.*

fun ViewGroup.inflateView(@LayoutRes layoutId: Int) =
    LayoutInflater.from(context).inflate(layoutId, this, false)

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}


