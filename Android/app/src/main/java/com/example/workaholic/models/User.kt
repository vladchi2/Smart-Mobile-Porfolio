package com.example.workaholic.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
        val id : String = "",
        val first_name : String = "",
        val last_name: String = "",
        val email: String = ""
):Parcelable