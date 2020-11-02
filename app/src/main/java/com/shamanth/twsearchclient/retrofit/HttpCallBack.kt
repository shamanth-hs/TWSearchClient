package com.shamanth.twsearchclient.retrofit

import com.shamanth.twsearchclient.db.TweetData

interface HttpCallBack {
    fun onSuccess(data:TweetData?)
    fun onFailure(t:Throwable)
}