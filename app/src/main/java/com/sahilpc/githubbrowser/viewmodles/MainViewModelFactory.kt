@file:Suppress("UNCHECKED_CAST")

package com.sahilpc.githubbrowser.viewmodles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sahilpc.githubbrowser.repository.RepoRepository


class MainViewModelFactory(private val repository: RepoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}