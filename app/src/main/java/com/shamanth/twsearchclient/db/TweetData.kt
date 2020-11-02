package com.shamanth.twsearchclient.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class TweetData(
    val `data`: List<Data>,
    val success: Boolean
) {

}

@Entity(tableName = "tweet_data")
data class Data(
    @ColumnInfo(name = "favorite_count")val favoriteCount: Int,
    @PrimaryKey
    @ColumnInfo(name = "handle")val handle: String,
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "profile_image_url")val profileImageUrl: String,
    @ColumnInfo(name = "retweet_count")val retweetCount: Int,
    @ColumnInfo(name = "text")val text: String
)