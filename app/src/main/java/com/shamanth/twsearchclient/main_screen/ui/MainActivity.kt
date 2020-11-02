package com.shamanth.twsearchclient.main_screen.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.shamanth.twsearchclient.R
import com.shamanth.twsearchclient.main_screen.viewmodel.TweetViewModel
import com.shamanth.twsearchclient.main_screen.viewmodel.TweetViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var searchQuery: TextInputLayout
    lateinit var searchButton: Button
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: TweetViewModel
    lateinit var group: Group
    lateinit var noData: TextView
    private val adapter = TweetAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initAdapter()
        initViewModel()
    }


    /*
    method to initialize all view components
     */
    private fun initView() {
        searchQuery = findViewById(R.id.search_query)
        searchButton = findViewById(R.id.search)
        recyclerView = findViewById(R.id.recyclerView)
        group = findViewById(R.id.group2)
        noData = findViewById(R.id.no_data)
        searchButton.setOnClickListener {
            val query = searchQuery.editText?.text.toString()
            if (query.isNotEmpty())
                viewModel.setQueryString(query)
        }
    }

    /*
    method to initialize adapter in recyclerview
     */
    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    /*
    method to initialize viewmodel
     */
    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            TweetViewModelFactory(application)
        ).get(TweetViewModel::class.java)

        //observing the livedata if it changes it will reflect in the UI
        viewModel.getTweetLiveData()?.observe(this) {
            //if there is no data show no data messgae
            if (it.isEmpty()) {
                if (group.visibility == View.VISIBLE)
                    group.visibility = View.GONE
                if (noData.visibility != View.VISIBLE)
                    noData.visibility = View.VISIBLE
            } else {
                //if data is there show it in recyclerview
                if (noData.visibility == View.VISIBLE)
                    noData.visibility = View.GONE
                if (group.visibility == View.VISIBLE)
                    group.visibility = View.GONE
                if (recyclerView.visibility != View.VISIBLE)
                    recyclerView.visibility = View.VISIBLE

                //submit data to the list adapter
                adapter.submitList(it)
            }
        }
    }
}