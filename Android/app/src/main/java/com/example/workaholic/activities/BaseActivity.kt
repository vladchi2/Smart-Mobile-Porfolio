package com.example.workaholic.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.workaholic.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_progress.*

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

        private lateinit var progressDialog: Dialog

    fun showSnackBar(message:String, errorMessage:Boolean)
    {
        val snackbar : Snackbar = Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view

        if (errorMessage){
            snackbarView.setBackgroundColor(
                ContextCompat.getColor(this@BaseActivity,
                    R.color.error
                )
            )
        }
        else
        {
            snackbarView.setBackgroundColor(
                ContextCompat.getColor(this@BaseActivity,
                    R.color.success
                )
            )
        }
        snackbar.show()
    }

    fun showProgress(text:String)
    {
        progressDialog = Dialog(this)

        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.tv_progress_view.text = text
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)

        progressDialog.show()
    }
    fun hideProgress()
    {
        progressDialog.dismiss()
    }
}