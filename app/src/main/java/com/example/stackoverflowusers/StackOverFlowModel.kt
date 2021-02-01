package com.example.stackoverflowusers

import android.net.Uri
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class StackOverFlowModel(val items: List<User>)

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("display_name")
    val displayName: String,

    @SerializedName("profile_image")
    val profileImage: String,

    @Embedded
    @SerializedName("badge_counts")
    val badgeCounts: BadgeCount
)

data class BadgeCount(
    val bronze: Int,
    val silver: Int,
    val gold: Int
)


