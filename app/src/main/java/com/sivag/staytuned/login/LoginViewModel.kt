package com.sivag.staytuned.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sivag.staytuned.database.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */
class LoginViewModel(private val repository: RegisterRepository) : ViewModel() {


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val errorToastUsername: LiveData<Boolean>
        get() = _errorToastUsername
    private val _errorToastUsername = MutableLiveData<Boolean>()

    val isGenuineUser: LiveData<Boolean>
        get() = _isGenuineUser
    private val _isGenuineUser = MutableLiveData<Boolean>()

    fun userPasswordValidation(userEmail: String,userPwd: String) {
        uiScope.launch {
            val usersEmails = repository.getUserEmail(userEmail)
            if (usersEmails != null) {
                _isGenuineUser.value = usersEmails.userPassword == userPwd
            } else {
                _errorToastUsername.value = true
            }
        }
    }

    fun deleteUserDBRequest() {
        uiScope.launch {
            repository.deleteAll()
        }
    }

}