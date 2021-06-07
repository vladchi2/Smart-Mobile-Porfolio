package com.example.workaholic.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.example.workaholic.R
import com.example.workaholic.firestore.FirestoreClass
import com.example.workaholic.models.User
import com.example.workaholic.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register_user.setOnClickListener(this)
        login_button.setOnClickListener(this)
        forgot_password.setOnClickListener(this)
    }

    override fun onClick(view:View?)
    {
        if (view !=null)
        {
            when (view.id){
                R.id.forgot_password -> {

                }
                R.id.login_button -> {
                    loginUser()
                }

                R.id.register_user -> {
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun loginUser()
    {
        if (validateLogin())
        {
            showProgress(resources.getString(R.string.please_wait))

            val email = email_login_tv.text.toString()
            val password = password_login_tv.text.toString()

            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener { task ->

                hideProgress()

                if (task.isSuccessful)
                {
                    FirestoreClass().getUserDetails(this@LoginActivity)
                    showSnackBar("You are logged in successfully",false)
                }
                else
                {
                    hideProgress()
                    showSnackBar(task.exception!!.message.toString(),true)
                }
            }
        }
    }
    private fun validateLogin():Boolean
    {

            if (email_login_tv.text.isNullOrBlank()) {
                showSnackBar("Please insert a valid email", true)
                return false
            }
            else if (password_login_tv.text.isNullOrBlank()) {
                showSnackBar("Insert  a password", true)
                return false
            }
            else {
                return true
            }
    }

    fun userLoggedInSuccess (user: User)
    {
        hideProgress()

        Log.e("First Name: ",user.first_name)
        Log.e("Last Name: ", user.last_name)
        Log.e("Email: ", user.email)


        @Suppress("Deprecation")
        Handler().postDelayed(
                {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
                    startActivity(intent)
                    finish()
                }, 1500)
    }

}