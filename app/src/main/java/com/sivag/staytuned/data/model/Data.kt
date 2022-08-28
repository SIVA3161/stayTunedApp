package com.sivag.staytuned.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val avatar: String,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("id")
    val userId: Int,

): Parcelable {
    fun getFullName(): String {
        return if (!firstName.isNullOrEmpty() && !lastName.isNullOrEmpty()) {
            "$firstName $lastName"
        } else {
            firstName
        }
    }
}