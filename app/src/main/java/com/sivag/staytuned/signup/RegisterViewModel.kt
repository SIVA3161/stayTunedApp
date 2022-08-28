package com.sivag.staytuned.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sivag.staytuned.database.RegisterEntity
import com.sivag.staytuned.database.RegisterRepository
import com.sivag.staytuned.databinding.ItemRegisterLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Siva G Gurusamy on 27/Aug/2022
 * email : sivaguru3161@gmail.com
 */

class RegisterViewModel(private val repository: RegisterRepository, application: Application) : AndroidViewModel(application) {


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _errorToastUsername = MutableLiveData<Boolean>()
    val errorToastUsername: LiveData<Boolean>
        get() = _errorToastUsername

    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

    fun saveUserToRDB(userFullName: String, userEmail: String, userPassword: String) {
        uiScope.launch {
            val userEmails = repository.getUserEmail(userEmail)
            if (userEmails != null) {
                _errorToastUsername.value = true
            } else {
                insert(RegisterEntity(0,userFullName,userEmail, userPassword))
            }
        }
    }

}