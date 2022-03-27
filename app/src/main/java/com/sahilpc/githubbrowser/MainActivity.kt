package com.sahilpc.githubbrowser

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahilpc.githubbrowser.adapters.MainRvAdapter
import com.sahilpc.githubbrowser.databinding.ActivityMainBinding
import com.sahilpc.githubbrowser.models.Repo
import com.sahilpc.githubbrowser.viewmodles.MainViewModel
import com.sahilpc.githubbrowser.viewmodles.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as RepoApplication).repoRepository

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)




        val recyclerview = findViewById<RecyclerView>(R.id.mainRv)
        recyclerview.layoutManager = LinearLayoutManager(this)


        mainViewModel.getRepoList().observe(this) {
            val adapter = MainRvAdapter(it, this)
            if (adapter.getItemCount() == 0) {
                binding.emptyList.visibility = View.VISIBLE
            } else {
                binding.emptyList.visibility = View.GONE
            }
            recyclerview.adapter = adapter
            adapter.notifyDataSetChanged()
        }


        binding.addRepoBtn.setOnClickListener{
           showDialog()
        }
        binding.addBtn.setOnClickListener{
            showDialog()
        }


    }
    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        val organization = dialog.findViewById(R.id.organisation) as EditText
        val repoName = dialog.findViewById(R.id.dialogName) as EditText
        val addBtn = dialog.findViewById(R.id.cd_addBtn) as Button
        val noBtn = dialog.findViewById(R.id.cd_close) as ImageView
        addBtn.setOnClickListener {
            mainViewModel.addRepo(repoName.text.toString(),organization.text.toString())
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()


    }

}