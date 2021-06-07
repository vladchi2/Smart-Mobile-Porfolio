package com.example.camera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

private const val REQUEST_CODE = 42

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val takePicBtn = findViewById<Button>(R.id.photoBut) as Button

        takePicBtn.setOnClickListener {
            val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (photoIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(photoIntent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "Unable to open camera", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        // i got a error with the imageview so i used the findViewById to find the id and use it with thee setImageBitmap
        val image = findViewById<ImageView>(R.id.image) as ImageView
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val photoMade = data?.extras?.get("data") as Bitmap
            image.setImageBitmap(photoMade)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}