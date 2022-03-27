package com.sahilpc.githubbrowser.viewmodles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahilpc.githubbrowser.models.Repo
import com.sahilpc.githubbrowser.repository.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RepoRepository) : ViewModel(){


    fun addRepo(repoName:String,organisation:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRepo(organisation,repoName)
        }
    }



    fun getRepoList(): LiveData<List<Repo>> {
        return repository.getList()
    }
}