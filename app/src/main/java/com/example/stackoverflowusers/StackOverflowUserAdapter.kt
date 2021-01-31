package com.example.stackoverflowusers

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.stackoverflowusers.StackOverflowUserAdapter.*

class StackOverflowUserAdapter(var users: List<User>):
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.stack_overflow_user_row, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.bindUser(user)
    }

    override fun getItemCount(): Int {
        return users.size
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val displayName = v.findViewById<TextView>(R.id.displayName)
        val itemImage = v.findViewById<ImageView>(R.id.profileImage)
        val goldBadge = v.findViewById<TextView>(R.id.goldBadge)
        val silverBadge = v.findViewById<TextView>(R.id.silverBadge)
        val bronzeBadge = v.findViewById<TextView>(R.id.bronzeBadge)
        fun bindUser(user: User) {
            displayName.text = user.displayName
            goldBadge.text = user.badgeCounts.gold.toString()
            silverBadge.text = user.badgeCounts.silver.toString()
            bronzeBadge.text = user.badgeCounts.bronze.toString()
            user?.profileImage?.let { imageUri ->
                itemImage.load(imageUri)

            }
        }
    }
}