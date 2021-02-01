package com.example.stackoverflowusers

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("/2.2/users?site=stackoverflow")
    suspend fun getStackOverflowUserData(@Query("inname") inname: String?, @Query("page") page: Int): Response<StackOverFlowModel>
}