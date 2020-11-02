package com.shamanth.twsearchclient.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Data::class), version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): DataDao


    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context:Context): AppDatabase {
            val tempInstance =
                INSTANCE
            if(tempInstance!=null)
                return tempInstance
            synchronized(true){
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "tweet_data"
                ).build()
                INSTANCE = db
                return db
            }
        }

    }
}

