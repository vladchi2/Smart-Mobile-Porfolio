package com.example.workaholic.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object Constants {
    const val USERS: String = "users"
    const val PREFERENCES : String = "MyPreferences"
    const val LOGGED_IN_USERNAME : String = "logged_in_username"
    const val EXTRA_USER_DETAILS : String = "extra_user_details"
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val IMAGE_REQUEST_CODE = 1

    const val JOB_IMAGE: String = "job_image"

    fun showImageChooser(activity:Activity)
    {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        activity.startActivityForResult(galleryIntent, IMAGE_REQUEST_CODE)
    }

    fun getFileExtension(activity: Activity,uri: Uri?):String?
    {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }
}