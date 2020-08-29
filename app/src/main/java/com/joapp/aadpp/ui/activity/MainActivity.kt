package com.joapp.aadpp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joapp.aadpp.R
import com.joapp.aadpp.ui.adapter.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() {
        viewPager.adapter = PagerAdapter(supportFragmentManager, this)
        tabLayout.setupWithViewPager(viewPager)
        submitButton.setOnClickListener {
            startActivity(Intent(this, SubmissionActivity::class.java))
        }
    }
}