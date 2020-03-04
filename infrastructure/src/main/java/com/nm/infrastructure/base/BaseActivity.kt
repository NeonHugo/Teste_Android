package com.nm.infrastructure.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.nm.infrastructure.R


abstract class BaseActivity : AppCompatActivity() {

    abstract val baseViewModel: BaseViewModel

    protected var lastClickTime: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(baseViewModel)

        window.navigationBarColor = ContextCompat.getColor(
            this,
            R.color.footerColor
        )
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}