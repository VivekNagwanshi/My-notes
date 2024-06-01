package com.example.mynotes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    var userId: Int,
    // val image: Int,
    val title: String,
    val description: String
)

