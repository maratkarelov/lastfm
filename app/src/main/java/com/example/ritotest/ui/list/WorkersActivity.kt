package com.example.ritotest.ui.list

import android.os.Bundle
import com.example.ritotest.R
import com.example.ritotest.core.ui.BaseActivity

class WorkersActivity : BaseActivity() {
    override fun getContentLayout(): Int {
        return R.layout.activity_with_fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = PerfomersFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}