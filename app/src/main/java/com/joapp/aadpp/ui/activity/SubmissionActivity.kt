package com.joapp.aadpp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joapp.aadpp.R
import kotlinx.android.synthetic.main.activity_submission.*

class SubmissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submission)
        init()
    }

    fun init() {
        backIcon.setOnClickListener { onBackPressed() }
    }
}