package com.nm.infrastructure.util.extensions.activity

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

fun AppCompatActivity.setupToolbar(
    toolbar: Toolbar?,
    showHome: Boolean
) {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        toolbar?.also {
            setSupportActionBar(it)
            setDisplayHomeAsUpEnabled(showHome)
            setDisplayShowHomeEnabled(showHome)
            setDisplayShowTitleEnabled(false)
        }
    }
}

fun AppCompatActivity.setupToolbarWithTitle(
    toolbar: Toolbar?,
    title: String,
    showHome: Boolean
) {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        toolbar?.title = title
        setSupportActionBar(toolbar)
        setDisplayHomeAsUpEnabled(showHome)
        setDisplayShowHomeEnabled(showHome)
    }
}

fun Context.getContextCompactDrawable(@DrawableRes drawableId: Int) = ContextCompat.getDrawable(this, drawableId)