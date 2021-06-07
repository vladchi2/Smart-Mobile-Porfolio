package com.example.workaholic.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.workaholic.R
import com.example.workaholic.utils.Constants
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val locButton = findViewById<Button>(R.id.locButton)

        jobsButton.setOnClickListener {
            val intent = Intent(this@MainActivity, JobActivity::class.java)

            startActivity(intent)
        }

        askHelpButton.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }
        val sharedPreferences = getSharedPreferences(Constants.PREFERENCES,Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(Constants.LOGGED_IN_USERNAME,"")!!
        tv_main.text = "Username : $username"

//        val button = findViewById<Button>(R.id.button)
//
//        button.setOnClickListener {
//            val intent = Intent(this, MainActivity2::class.java)
//
//            startActivity(intent)
//        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

//        findViewById<Button>(R.id.bt_get_location).setOnClickListener {
//            fetchLocation()
//        }
    }
    private fun fetchLocation() {
        val task = fusedLocationProviderClient.lastLocation

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
        task.addOnSuccessListener {
            if(it != null){
                Toast.makeText(applicationContext,"${it.latitude} ${it.longitude}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}