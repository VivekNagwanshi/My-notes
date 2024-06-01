package com.example.mynotes.user
import androidx.lifecycle.LiveData
import com.example.mynotes.UserData

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<UserData>> = userDao.readAllData()
    suspend fun addUser(userData: UserData) {
        userDao.addUser(userData)
    }

    suspend fun updateUser(userData: UserData) {
        userDao.updateUser(userData)
    }

    suspend fun deleteUser(userData: UserData) {
        userDao.deleteUser(userData)
    }

    fun getUserData(userData: Int): UserData {
        return userDao.getUserData(userData)
    }
}