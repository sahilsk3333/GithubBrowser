package com.sahilpc.githubbrowser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sahilpc.githubbrowser.databinding.ActivityRepoDetailBinding
import com.sahilpc.githubbrowser.viewmodles.DetailViewModel
import com.sahilpc.githubbrowser.viewmodles.DetailViewModelFactory



class RepoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoDetailBinding
   private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repoName = intent.getStringExtra("repoName")
        val repoDes = intent.getStringExtra("repoDes")
        val repoIssues = intent.getStringExtra("Issues")
        val repoUrl = intent.getStringExtra("repoUrl")
        val id: Int = intent.getIntExtra("repoId", 0)

        binding.repoName.text =  repoName
        binding.repoDes.text = repoDes
        binding.issues.text = "Issues : $repoIssues"

        val repository = (application as RepoApplication).repoRepository
        detailViewModel = ViewModelProvider(this, DetailViewModelFactory(repository))[DetailViewModel::class.java]

        binding.backBtn.setOnClickListener { onBackPressed() }
        binding.deleteRepo.setOnClickListener {
            detailViewModel.deleteRepo(id)
            onBackPressed()
        }
        binding.openBrowser.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(repoUrl))
            startActivity(browserIntent)
        }

    }

}