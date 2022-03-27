package com.sahilpc.githubbrowser.viewmodles


import androidx.lifecycle.ViewModel
import com.sahilpc.githubbrowser.db.RepoDatabase
import com.sahilpc.githubbrowser.repository.RepoRepository

class DetailViewModel(private val repository: RepoRepository) : ViewModel() {

    fun deleteRepo(id: Int) {
        repository.deleteRepo(id)
    }
}