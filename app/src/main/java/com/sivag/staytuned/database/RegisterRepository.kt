package com.sivag.staytuned.database


/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */
class RegisterRepository(private val dao: RegisterDatabaseDao) {

    val users = dao.getAllUsers()

    suspend fun insert(user: RegisterEntity) {
        return dao.insert(user)
    }

    suspend fun getUserEmail(userEmail: String):RegisterEntity?{
        return dao.getUserEmail(userEmail)
    }

//    suspend fun deleteAll(): Int {
//        return dao.deleteAll()
//    }

}