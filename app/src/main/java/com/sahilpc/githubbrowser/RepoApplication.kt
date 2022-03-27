package com.sahilpc.githubbrowser

import android.app.Application
import com.sahilpc.githubbrowser.api.RepoService
import com.sahilpc.githubbrowser.api.RetrofitHelper
import com.sahilpc.githubbrowser.db.RepoDatabase
import com.sahilpc.githubbrowser.repository.RepoRepository

class RepoApplication : Application(){
    lateinit var repoRepository: RepoRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val repoService = RetrofitHelper.getInstance().create(RepoService::class.java)
        val database = RepoDatabase.getDatabase(applicationContext)
        repoRepository = RepoRepository(repoService, database, applicationContext)
    }

}