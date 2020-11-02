package com.shamanth.twsearchclient.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DataDao {
    @Query("SELECT * from tweet_data")
    fun getAll(): LiveData<List<Data>>

    @Query("SELECT * FROM tweet_data where name LIKE :queryString or handle LIKE :queryString or text LIKE :queryString")
    fun getTweetFromString(queryString:String) : LiveData<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: Data)

    @Delete
    fun delete(user: Data)
}