package com.shamanth.twsearchclient.main_screen.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.shamanth.twsearchclient.db.AppDatabase
import com.shamanth.twsearchclient.db.Data
import com.shamanth.twsearchclient.db.TweetData
import com.shamanth.twsearchclient.db.TweetRepository
import com.shamanth.twsearchclient.retrofit.HttpCallBack
import com.shamanth.twsearchclient.retrofit.TweetService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TweetViewModel(application: Application) : AndroidViewModel(application) {
    private var _queryString = MutableLiveData<String>() //data to update the query string

    private val repository: TweetRepository

    init {
        val tweetDao = AppDatabase.getInstance(application).userDao()
        repository = TweetRepository(tweetDao)
        fetchDataFromApi()
    }

    //observe the data change in queryString if there is a change get the updated data
    private var tweettData: LiveData<List<Data>> = Transformations.switchMap(_queryString){
        repository.getDataFromQuery(_queryString.value!!)
    }

    //add the tweets to the rooms DB
    fun addTweet(data: List<Data>) {
        viewModelScope.launch(Dispatchers.IO) {
            for (eachData in data)
                repository.insertAllTweetData(eachData)
        }
    }

    //get data from the given API
    private fun fetchDataFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            TweetService().getTweets(object : HttpCallBack {
                override fun onSuccess(data: TweetData?) {
                    if (data?.success!!)
                        addTweet(data.data)
                }

                override fun onFailure(t: Throwable) {
                }

            })
        }

    }

    fun setQueryString(value:String){
        _queryString.value = value
    }

    fun getTweetLiveData(): LiveData<List<Data>>? {
        return tweettData
    }

}