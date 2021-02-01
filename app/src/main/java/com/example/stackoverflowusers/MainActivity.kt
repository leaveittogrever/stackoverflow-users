package com.example.stackoverflowusers

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: StackOverflowUserAdapter
    private lateinit var viewModel: StackOverflowUsersViewModel
    private var searchName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataSource = AppDatabase.getInstance(application).userDao()
        viewModel = StackOverflowUsersViewModel(dataSource, application)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        launch {
            adapter = StackOverflowUserAdapter(viewModel.getUsers())
            recyclerView.adapter = adapter

        }
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )

        setClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    private fun setClickListeners() {
        nextPageButton.setOnClickListener {
            viewModel.page += 1
            launch(Dispatchers.Main) {
                callApiAndUpdateAdapter()
            }
        }

        searchButton.setOnClickListener {
            val newSearchName = editTextTextPersonName.text?.toString()
            val inputManager: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(it.getWindowToken(), 0)
            launch(Dispatchers.Main) {
                if (searchName != newSearchName) {
                    viewModel.clearUsersForNewSearch()
                    adapter.users = emptyList()
                    adapter.notifyDataSetChanged()
                    searchName = newSearchName
                }
                val users = callApiAndUpdateAdapter()
                if (users.isNotEmpty()) {
                    nextPageButton.visibility = View.VISIBLE
                }
            }
        }
    }

    private suspend fun callApiAndUpdateAdapter(): List<User> {
        val users = viewModel.callStackOverflowUsersApi(searchName)
        val currentUsers = adapter.users
        adapter.users = currentUsers + users
        adapter.notifyDataSetChanged()
        return users
    }
}