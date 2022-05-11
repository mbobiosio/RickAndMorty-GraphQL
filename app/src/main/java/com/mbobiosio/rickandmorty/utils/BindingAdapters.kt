package com.mbobiosio.rickandmorty.utils

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.mbobiosio.rickandmorty.R

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    load(url)
}

@BindingAdapter("characterStatus")
fun TextView.characterStatus(status: String?) {
    when {
        status.equals("Alive") -> {
            text = context.getString(R.string.alive)
            setTextColor(Color.GREEN)
        }
        status.equals("Dead") -> {
            text = context.getString(R.string.dead)
            setTextColor(Color.RED)
        }
        else -> {
            text = context.getString(R.string.unknown)
            setTextColor(Color.YELLOW)
        }
    }
}
