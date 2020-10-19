package com.example.lastfm.ui.list

import android.os.Bundle
import com.example.lastfm.R
import com.example.lastfm.core.ui.BaseActivity

class ArtistsActivity : BaseActivity() {
    override fun getContentLayout(): Int {
        return R.layout.activity_with_fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = ArtistsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}