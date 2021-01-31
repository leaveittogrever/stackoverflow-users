package com.example.stackoverflowusers

import android.net.Uri
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class StackOverFlowModel(val items: List<User>)

data class User(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("display_name")
    val displayName: String,

    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("badge_counts")
    val badgeCounts: BadgeCount
)

data class BadgeCount(
    val bronze: Int,
    val silver: Int,
    val gold: Int
)


