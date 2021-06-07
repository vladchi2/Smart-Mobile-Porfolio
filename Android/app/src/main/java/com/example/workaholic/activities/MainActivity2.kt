package com.example.workaholic.activities

import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.workaholic.R
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*
import java.util.jar.Manifest


class MainActivity2 : AppCompatActivity()  {
//    var btLocation: Button? = null
//    var textView1: TextView? = null
//    var textView2: TextView? = null
//    var textView3: TextView? = null
//    var textView4: TextView? = null
//    var textView5: TextView? = null
//    var fusedLocationProviderClient: FusedLocationProviderClient? = null

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        getPos.setOnClickListener {
            getLastLocation()
        }

        //OLD WAY
//        findViewById<Button>(R.id.bt_get_location).setOnClickListener {
//            fetchLocation()
//        }

    }


//                                          OLD WAY
//    private fun fetchLocation() {
//        val task = fusedLocationProviderClient.lastLocation
//
//        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//        ){
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
//            return
//        }
//        task.addOnSuccessListener {
//            if(it != null){
//                Toast.makeText(applicationContext,"${it.latitude} ${it.longitude}", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//////////////////////
    private var PERMISSION_ID = 1000

    private fun CheckPermission():Boolean{
        if(
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }
        return false
    }

    private fun RequestPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSION_ID
        )
    }

    private fun isLocationEnabled():Boolean{

        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == PERMISSION_ID){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("Debug: ", "You Have the Permission")
            }
        }

       // super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun getCityName(lat:Double,long:Double):String{
        var CityName = ""
        var geoCoder = Geocoder(this, Locale.getDefault())
        var Adress = geoCoder.getFromLocation(lat,long,1)

        CityName = Adress.get(0).locality
        return  CityName
    }

    private fun getCountryName(lat:Double,long:Double):String{
        var countryName = ""
        var geoCoder = Geocoder(this, Locale.getDefault())
        var Adress = geoCoder.getFromLocation(lat,long,1)

        countryName = Adress.get(0).countryName
        return  countryName
    }

    private fun getLastLocation(){
        if (CheckPermission()){
           if(isLocationEnabled()){

               fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                   var location : Location? = task.result
                   if (location == null) {

                    getNewLocation()
                   }else{
                       Locationtxt.text = "Your Current Coordinates are :\nLat:" + location.latitude + " ; Long:" + location.longitude+
                               "\nYour City: "+getCityName(location.latitude,location.longitude) + ", your Country: "+ getCountryName(location.latitude,location.longitude)
                   }
               }
           }else{
               Toast.makeText(this,"Please Enable your Location Service",Toast.LENGTH_SHORT).show()
           }

        }else{
            RequestPermission()
        }
    }
private fun getNewLocation(){
    locationRequest = LocationRequest()
    locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    locationRequest.interval = 0
    locationRequest.fastestInterval = 0
    locationRequest.numUpdates = 2
    fusedLocationProviderClient!!.requestLocationUpdates(
        locationRequest,locationCallback, Looper.myLooper()
    )
}
    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            var lastLocation = p0.lastLocation

            Locationtxt.text = "Your Current Coordinates are :\nLat:" + lastLocation.latitude + " ; Long:" + lastLocation.longitude+
                    "\nYour City: "+getCityName(lastLocation.latitude,lastLocation.longitude) + ", your Country: "+ getCountryName(lastLocation.latitude,lastLocation.longitude)
            //super.onLocationResult(p0)
        }
    }
}
//
//        //Assign variable
//        btLocation = findViewById(R.id.bt_location);
//        textView1 = findViewById(R.id.text_view1);
//        textView2 = findViewById(R.id.text_view2);
//        textView3 = findViewById(R.id.text_view3);
//        textView4 = findViewById(R.id.text_view4);
//        textView5 = findViewById(R.id.text_view5);
//
//        //Initialize fusedLocationProviderClient
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//
//        btLocation.setOnClickListener(new View.OnclickListener() {
//            @Override
//            public void onClick(View v) {
//                //Check permission
//                if (ActivityCompat.checkSelfPermission(MainActivity.this
//                                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
//                {
//                    getLocation()
//                }
//                else {
//                    ActivityCompat.requestPermissions(MainActivity.this
//                            ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
//                }
//
//            }
//        });

