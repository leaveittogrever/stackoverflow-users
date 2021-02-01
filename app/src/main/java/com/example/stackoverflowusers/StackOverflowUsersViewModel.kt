package com.example.stackoverflowusers

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class StackOverflowUsersViewModel(
    val database: UserDao,
    application: Application,
) : AndroidViewModel(application) {
    var page = 1

    suspend fun getUsers() : List<User> {
        return database.getAll()
    }

    suspend fun clearUsersForNewSearch() {
        page = 1
        database.deleteAll()
    }

    suspend fun callStackOverflowUsersApi(userName: String?, page: Int = 1): List<User> {
        val response = ApiAdapter.apiClient.getStackOverflowUserData(userName, page)
        if (response.isSuccessful) {
            val data = response.body()!!
            database.insertAll(data.items)
            val users = database.getAll()

            return users

        } else {
            return database.getAll()
        }

    }
}