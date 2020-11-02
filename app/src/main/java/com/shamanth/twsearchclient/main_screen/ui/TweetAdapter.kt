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
        val profileImage: ImageView = view.findViewById(R.id.profile_image)
        val userName = view.findViewById<TextView>(R.id.user_name)
        val userHandle: TextView = view.findViewById(R.id.twitter_handle)
        val messageText: TextView = view.findViewById(R.id.tweet_text)
        val retweetCount: TextView = view.findViewById(R.id.retweet_count)
        val likesCount: TextView = view.findViewById(R.id.likes_count)

        fun bind(data: Data) {
//            val url = URL(data.profileImageUrl)
//            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//            profileImage.setImageBitmap(bmp)
//            profileImage.setImageResource(R.drawable.ic_person_24dp)
            val imageUrl = if (URLUtil.isValidUrl(data.profileImageUrl))
                Uri.parse(data.profileImageUrl.trim()).path
            else
                ""
            Log.e("tweetAdapter", "bind: $imageUrl")
//            TweetService().getHtmldata(imageUrl)
            Glide.with(context)
                .load(data.profileImageUrl)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .timeout(6000)
                .error(R.drawable.ic_person_24dp)
                .dontAnimate()
                .dontTransform()
                .into(profileImage)
//            Picasso.with(context)
//                .load(imageUrl)
//                .error(R.drawable.ic_person_24dp)
//                .into(profileImage)
            userName.text = data.name
            userHandle.text = data.handle
            messageText.text = data.text
            retweetCount.text = data.retweetCount.toString()
            likesCount.text = data.favoriteCount.toString()
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

