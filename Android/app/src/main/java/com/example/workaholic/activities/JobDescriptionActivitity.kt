package com.example.workaholic.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workaholic.R
import kotlinx.android.synthetic.main.activity_job_description_activitity.*
import kotlinx.android.synthetic.main.activity_main.*

class JobDescriptionActivitity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_description_activitity)

        button3.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)

            startActivity(intent)
        }
    }
}