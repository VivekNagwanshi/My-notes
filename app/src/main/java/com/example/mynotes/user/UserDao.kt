package com.example.mynotes.user

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynotes.UserData

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(users: UserData)

    @Update
    fun updateUser(users: UserData)

    @Delete
    fun deleteUser(users: UserData)

    @Query("SELECT * FROM user ORDER BY userId ASC")
    fun readAllData(): LiveData<List<UserData>>

    @Query("SELECT * FROM user where userId=:userId")
    fun getUserData(userId: Int): UserData

}
