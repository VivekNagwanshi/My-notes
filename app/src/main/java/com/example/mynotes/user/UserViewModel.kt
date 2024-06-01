package com.example.mynotes.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mynotes.UserData
import com.example.mynotes.UserDatabase
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {
    val realAllData: LiveData<List<UserData>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application)!!.userDao()
        repository = UserRepository(userDao)
        realAllData = repository.readAllData
    }

    fun addUser(userData: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(userData)
        }
    }

    fun updateUser(userData: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(userData)
        }
    }

    fun getUserData(userData: Int): UserData {
        return repository.getUserData(userData)
    }

    fun deleteUser(userData: UserData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(userData)
        }
    }
}