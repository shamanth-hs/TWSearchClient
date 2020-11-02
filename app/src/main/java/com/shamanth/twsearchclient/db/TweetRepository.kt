package com.shamanth.twsearchclient.db

import com.shamanth.twsearchclient.db.Data
import com.shamanth.twsearchclient.db.DataDao

class TweetRepository(private val dataDao: DataDao) {

    fun getAllData() = dataDao.getAll()

    fun insertAllTweetData(data: Data) = dataDao.insertAll(data)

    fun getDataFromQuery(queryString:String) = dataDao.getTweetFromString("%$queryString%")
}