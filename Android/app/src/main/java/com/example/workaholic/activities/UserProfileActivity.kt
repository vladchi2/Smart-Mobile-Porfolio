package com.example.workaholic.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.workaholic.R
import com.example.workaholic.firestore.FirestoreClass
import com.example.workaholic.models.User
import com.example.workaholic.utils.Constants
import com.example.workaholic.utils.GlideLoader
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_user_profile.*
import java.io.IOException
import java.util.HashMap

class UserProfileActivity : BaseActivity(), View.OnClickListener {

    var mSelectedImage : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        var userDetails : User = User()

        post_job_butt.setOnClickListener{
            val title:String = job_title.text.toString()
            val description: String = job_description.text.toString()
            saveFirebase(title,description)
        }

        post_job_butt.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        if(intent.hasExtra(Constants.EXTRA_USER_DETAILS))
        {
            //Get user detail from intent as Parcelable
            userDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }

        iv_user_photo.setOnClickListener(this@UserProfileActivity)
    }

    override fun onClick(v: View?) {
        if (v != null)
        {
            when (v.id)
            {
                R.id.iv_user_photo -> {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        Constants.showImageChooser(this)
                    }
                    else
                    {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE
                        )
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE)
        {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                showSnackBar("Storage Permission Granted", false)
                Constants.showImageChooser(this)
            }
            else {
                showSnackBar("Permission Denied!",true)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        showProgress("Please wait")
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.IMAGE_REQUEST_CODE) {
                if (data != null) {
                    try {
                        mSelectedImage = data.data!!

                        //iv_user_photo.setImageURI(selectedImageURI)
                        GlideLoader(this).loadUserPicture(mSelectedImage!!,iv_user_photo)
                        FirestoreClass().uploadImageToCloudStorage(this,mSelectedImage)
                    } catch (e: IOException) {
                        showSnackBar("Image selection failed", true)
                    }
                }
            }
        }
    }

    fun imageUploadSuccess(ImgUrl:String){
        hideProgress()
        showSnackBar("Success Upload. URL: $ImgUrl",false)
    }

    fun saveFirebase(title:String,description:String)
    {
        val db = FirebaseFirestore.getInstance()
        val job:MutableMap<String,Any> = HashMap()
        job["title"] = title
        job["description"] = description
        job["image"] = mSelectedImage.toString()

        db.collection("jobs").add(job)
            .addOnSuccessListener {
                showSnackBar("Added successful!",false)
            }
            .addOnFailureListener {
                showSnackBar("Failed to add!",true)
            }
    }

}