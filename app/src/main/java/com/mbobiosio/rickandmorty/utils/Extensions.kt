package com.mbobiosio.rickandmorty.utils

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified T : ViewDataBinding> ViewGroup.inflate(
    @LayoutRes layout: Int,
    attachToRoot: Boolean = false
): T = DataBindingUtil.inflate(
    LayoutInflater.from(context),
    layout,
    this,
    attachToRoot
)

fun View.setSafeClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickLister { onSafeClick(it) }
    setOnClickListener(safeClickListener)
}

class SafeClickLister(
    private var defaultInterval: Int = 1000,
    private val onSafeClick: (View) -> Unit
) : View.OnClickListener {
    private var lastItemClicked: Long = 0
    override fun onClick(v: View) {
        when {
            SystemClock.elapsedRealtime() - lastItemClicked < defaultInterval -> return
            else -> {
                lastItemClicked = SystemClock.elapsedRealtime()
                onSafeClick(v)
            }
        }
    }
}

fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}

inline fun <reified T> jsonConverter(): Type = object : TypeToken<T>() {}.type
