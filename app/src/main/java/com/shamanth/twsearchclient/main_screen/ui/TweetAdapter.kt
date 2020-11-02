package com.shamanth.twsearchclient.main_screen.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.shamanth.twsearchclient.R
import com.shamanth.twsearchclient.db.Data
import com.shamanth.twsearchclient.retrofit.TweetService
import com.squareup.picasso.Picasso
import java.io.File
import java.net.URI
import java.net.URL


class TweetAdapter(val context: Context) : ListAdapter<Data, TweetAdapter.MyViewHolder>(
    MyDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tweet_card, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val profileImage: ImageView = view.findViewById(R.id.profile_image)
        private val userName: TextView = view.findViewById(R.id.user_name)
        private val userHandle: TextView = view.findViewById(R.id.twitter_handle)
        private val messageText: TextView = view.findViewById(R.id.tweet_text)
        private val retweetCount: TextView = view.findViewById(R.id.retweet_count)
        private val likesCount: TextView = view.findViewById(R.id.likes_count)

        fun bind(data: Data) {

            userName.text = data.name
            userHandle.text = data.handle
            messageText.text = data.text
            retweetCount.text = data.retweetCount.toString()
            likesCount.text = data.favoriteCount.toString()


            val imageUrl = if (URLUtil.isValidUrl(data.profileImageUrl))
                Uri.parse(data.profileImageUrl.trim()).path
            else
                ""
            Log.d("tweetAdapter", "bind: $imageUrl")

           // TODO("have to check the URL weather the image is present or not")
            Glide.with(context)
                .load(data.profileImageUrl)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .timeout(6000)
                .error(R.drawable.ic_person_24dp)
                .dontAnimate()
                .dontTransform()
                .into(profileImage)

            /*
            Alternate way by using picasso

            Picasso.with(context)
            .load(imageUrl)
            .error(R.drawable.ic_person_24dp)
            .into(profileImage)

             */


        }
    }


    class MyDiffCallback : DiffUtil.ItemCallback<Data>() {

        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
}

