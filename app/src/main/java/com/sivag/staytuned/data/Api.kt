package com.sivag.staytuned.data

import com.sivag.staytuned.data.model.ContactsModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Siva G Gurusamy on 28/Aug/2022
 * email : sivaguru3161@gmail.com
 */
interface Api {

    @GET( "users?page=1")
    fun getContactsData(): Call<ContactsModel>


}