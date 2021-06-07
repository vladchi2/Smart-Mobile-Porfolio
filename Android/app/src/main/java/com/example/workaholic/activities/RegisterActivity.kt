package com.example.workaholic.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.workaholic.R
import com.example.workaholic.firestore.FirestoreClass
import com.example.workaholic.models.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_butt.setOnClickListener {
                register()
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

    }


    private fun register() {

        if (validateRegistration())
        {
            showProgress(resources.getString(R.string.please_wait))

            val password: String = password_reg.text.toString()
            val email: String = email_reg.text.toString()

            //Create an instance and register with email and passowrd
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(OnCompleteListener { task ->
                        //If registration successfully done
                        if (task.isSuccessful) {
                            //Firebase register user
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            val user = User(
                                    firebaseUser.uid,
                                    first_name_reg.text.toString(),
                                    last_name_reg.text.toString(),
                                    email_reg.text.toString()
                            )


                            FirestoreClass().registerUser(this@RegisterActivity,user)

                            //FirebaseAuth.getInstance().signOut()
                            //finish()
                        } else {
                            //Registration failed -> display error
                            hideProgress()
                            showSnackBar("Registration failed! ${task.exception!!.message.toString()}", true)
                        }
                    })
        }
    }

    private fun validateRegistration(): Boolean {
        if (email_reg.text.isNullOrBlank()) {
            showSnackBar("Please insert a valid email", true)
            return false
        }
        else if (password_reg.text.isNullOrBlank()) {
            showSnackBar("Select a password", true)
            return false
        }
        else {
           return true
        }

    }

    fun userRegistrationSuccess()
    {
        hideProgress()

        showSnackBar("Registration successfully done!", false)
    }
}