package com.sahilpc.githubbrowser.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sahilpc.githubbrowser.models.Repo

@Database(entities = [Repo::class], version = 1,exportSchema = false)
abstract class RepoDatabase : RoomDatabase (){

    abstract fun repoDao() : RepoDao

    companion object{
        @Volatile
        private var INSTANCE: RepoDatabase? = null

        fun getDatabase(context: Context): RepoDatabase {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                        RepoDatabase::class.java,
                        "repoDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}