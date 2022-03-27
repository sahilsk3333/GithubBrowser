package com.sahilpc.githubbrowser.viewmodles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sahilpc.githubbrowser.repository.RepoRepository


class DetailViewModelFactory(private val repository: RepoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }

}