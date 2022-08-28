package com.sivag.staytuned.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sivag.staytuned.database.RegisterRepository

/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */
class RegisterViewModelFactory(private var repository: RegisterRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown View Model Class")
        }
    }
}