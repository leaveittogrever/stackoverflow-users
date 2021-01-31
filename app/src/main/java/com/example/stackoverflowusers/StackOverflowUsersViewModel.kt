package com.example.stackoverflowusers

import android.util.Log
import androidx.lifecycle.ViewModel


class StackOverflowUsersViewModel : ViewModel() {

    suspend fun callStackOverflowUsersApi(userName: String?): List<User> {
        val response = ApiAdapter.apiClient.getStackOverflowUserData(userName)
        if (response.isSuccessful) {
            val data = response.body()!!
            return data.items

        } else {
            Log.d("PAUL", "NOPE!")
            return emptyList()
        }

    }
}