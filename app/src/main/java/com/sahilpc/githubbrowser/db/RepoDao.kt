package com.sahilpc.githubbrowser.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sahilpc.githubbrowser.models.Repo

@Dao
interface RepoDao {
    @Insert
    suspend fun addRepo(repo : Repo)

    @Query("DELETE FROM repos WHERE repoId = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM repos")
     fun getRepos():LiveData<List<Repo>>
}