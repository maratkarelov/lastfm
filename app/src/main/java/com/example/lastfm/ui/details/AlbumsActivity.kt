package com.example.lastfm.ui.details

import android.os.Bundle
import com.example.lastfm.R
import com.example.lastfm.core.ui.BaseActivity

class AlbumsActivity : BaseActivity() {
    override fun getContentLayout(): Int {
        return R.layout.activity_with_fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = AlbumsFragment()
        val bundle: Bundle? = intent.extras
        fragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}