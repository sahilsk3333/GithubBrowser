package com.sahilpc.githubbrowser.api

import com.sahilpc.githubbrowser.models.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {
    @GET("/repos/{repowner}/{repo}")
    suspend fun getRepo(@Path("repowner")repowner:String,@Path("repo")repo:String):Response<Repo>
}