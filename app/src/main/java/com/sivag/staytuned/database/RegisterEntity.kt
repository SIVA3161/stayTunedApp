package com.sivag.staytuned.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */
@Entity(tableName = "Register_user_table")
data class RegisterEntity(

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "full_name")
    var fullName: String,

    @ColumnInfo(name = "user_email")
    var userEmail: String,

    @ColumnInfo(name = "user_password")
    var userPassword: String
)
