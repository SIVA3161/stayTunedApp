package com.sivag.staytuned.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sivag.staytuned.database.RegisterRepository
import java.lang.IllegalArgumentException

/**
 * Created by Siva G Gurusamy on 27/Aug/2022
 * email : sivaguru3161@gmail.com
 */

//
//class LoginViewModelFactory(
//    private  val repository: RegisterRepository,
//    private val application: Application
//): ViewModelProvider.Factory{
//    @Suppress("Unchecked_cast")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
//            return LoginViewModel(repository, application) as T
//        }
//        throw IllegalArgumentException("Unknown View Model Class")
//    }
//}