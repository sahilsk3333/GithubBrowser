package com.sahilpc.githubbrowser.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.sahilpc.githubbrowser.api.RepoService
import com.sahilpc.githubbrowser.db.RepoDatabase
import com.sahilpc.githubbrowser.models.Repo
import com.sahilpc.githubbrowser.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RepoRepository (
    private val repoService: RepoService,
    private val repoDatabase: RepoDatabase,
    private val applicationContext: Context
    ){


    fun getList():LiveData<List<Repo>>{
        return repoDatabase.repoDao().getRepos()
    }

    fun deleteRepo(id:Int){

        GlobalScope.launch (Dispatchers.IO){
            repoDatabase.repoDao().deleteById(id)
        }

    }

    suspend fun getRepo(repowner:String,repo:String){

        repoDatabase.repoDao().getRepos()
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = repoService.getRepo(repowner,repo)
            if(result.body() != null){
                repoDatabase.repoDao().addRepo(result.body()!!)
            }
        }

    }

}