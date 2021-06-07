package com.example.workaholic.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.example.workaholic.activities.LoginActivity
import com.example.workaholic.activities.RegisterActivity
import com.example.workaholic.models.User
import com.example.workaholic.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.example.workaholic.activities.BaseActivity
import com.example.workaholic.activities.UserProfileActivity

class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo: User)
    {

        mFireStore.collection(Constants.USERS).document(userInfo.id).set(userInfo, SetOptions.merge())
                .addOnSuccessListener {
                    activity.userRegistrationSuccess()
                }
                .addOnFailureListener { e ->
                activity.hideProgress()
                Log.e(
                        activity.javaClass.simpleName,
                        "Error registering the user!",
                        e
                )}
    }

    private fun getUserById():String
    {
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserID = ""
        if (currentUser != null)
        {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    fun getUserDetails(activity : Activity)
    {
        //Collection name -> Database name
        mFireStore.collection(Constants.USERS)
                .document(getUserById())
                .get()
                //Document which we want to get -> specific user
                .addOnSuccessListener { document ->
                    Log.i(activity.javaClass.simpleName, document.toString())

                    val user = document.toObject(User::class.java)!!

                    //Create a shared preferences variable to store firstname and lastname
                    val sharedPreference = activity.getSharedPreferences(
                            Constants.PREFERENCES,
                            Context.MODE_PRIVATE
                    )
                    //Key - Value => username - firstname lastname
                    val editor: SharedPreferences.Editor = sharedPreference.edit()
                    editor.putString(
                            Constants.LOGGED_IN_USERNAME,
                            "${user.first_name} ${user.last_name}"
                    )
                    editor.apply()

                    when(activity)
                    {
                        is LoginActivity -> {
                            activity.userLoggedInSuccess(user)
                        }
                    }
                }
                .addOnFailureListener { e ->
                    when(activity){
                        is LoginActivity -> {
                            activity.hideProgress()
                        }
                    }
                    Log.e(
                            activity.javaClass.simpleName,
                            "Error! ${e.message.toString()}"
                    )
                }
    }

    fun uploadImageToCloudStorage(activity: Activity,uri: Uri?) {
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            Constants.JOB_IMAGE + System.currentTimeMillis() + "."
                    + Constants.getFileExtension(activity, uri)
        )
        sRef.putFile(uri!!).addOnSuccessListener { taskSnapshot ->
            Log.e(
                "Firebase Image URL", taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
            )
            taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
                Log.e("Downloadable Image URl", uri.toString())
                when (activity) {
                    is UserProfileActivity -> {
                        activity.imageUploadSuccess(uri.toString())
                    }
                }
            }
        }
            .addOnFailureListener { exception ->
                when (activity) {
                    is UserProfileActivity -> {
                        activity.hideProgress()
                    }
                }
                Log.e(
                    activity.javaClass.simpleName,
                    exception.message,
                    exception

                    )
            }
    }



}