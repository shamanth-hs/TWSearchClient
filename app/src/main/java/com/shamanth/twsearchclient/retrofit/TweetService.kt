package com.shamanth.twsearchclient.retrofit

import android.util.Log
import com.shamanth.twsearchclient.db.TweetData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class TweetService : BaseService() {

    //APi call to get the data from the API
    fun getTweets(httpCallBack: HttpCallBack) {
        val instance = retrofit.create(TweetInterface::class.java)
        val call = instance.getTweets()
        call.enqueue(object : Callback<TweetData> {
            override fun onFailure(call: Call<TweetData>, t: Throwable) {
                httpCallBack.onFailure(t)
            }

            override fun onResponse(call: Call<TweetData>, response: Response<TweetData>) {
                if (response.isSuccessful)
                    httpCallBack.onSuccess(response.body())
            }

        })

    }

    //currently not being used
//    fun getHtmldata(path:String?) {
//        val instance = imageRetrofit.create(TweetInterface::class.java)
//        val call = instance.getHtml(path)
//        call.enqueue(object : Callback<ResponseBody> {
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                t.printStackTrace()
////                httpCallBack.onFailure(t)
//            }
//
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                if (response.isSuccessful)
//                    Log.i("apicall", "onResponse: ${response.raw()}")
//            }
//
//        })

//    }


    interface TweetInterface {
        @GET("tweets")
        fun getTweets(): Call<TweetData>

//        @GET("{path}")
//        fun getHtml(@Path("path") path:String?):Call<ResponseBody>
    }


}