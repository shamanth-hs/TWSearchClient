package com.shamanth.twsearchclient.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shamanth.twsearchclient.utility.AppConstants

@Database(entities = [Data::class], version = 1,exportSchema = false)
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
                    AppDatabase::class.java, AppConstants.DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }

    }
}

