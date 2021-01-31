package com.example.stackoverflowusers

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: StackOverflowUserAdapter
    private lateinit var viewModel: StackOverflowUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = StackOverflowUsersViewModel()
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = StackOverflowUserAdapter(emptyList<User>())
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )

        searchButton.setOnClickListener {
            val searchName = editTextTextPersonName.text?.toString()
            launch(Dispatchers.Main) {
                val users = viewModel.callStackOverflowUsersApi(searchName)
                adapter.users = users
                adapter.notifyDataSetChanged()
                if (users.isNotEmpty()) {
                    nextPageButton.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}