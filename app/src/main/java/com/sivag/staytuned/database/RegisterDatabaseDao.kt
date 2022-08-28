package com.sivag.staytuned.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */

@Dao
interface RegisterDatabaseDao {

    @Insert
    suspend fun insert(register: RegisterEntity)

    @Query("SELECT * FROM Register_user_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

    @Query("DELETE FROM Register_user_table")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM Register_user_table WHERE user_email LIKE :userEmail")
    suspend fun getUserEmail(userEmail: String): RegisterEntity?

}