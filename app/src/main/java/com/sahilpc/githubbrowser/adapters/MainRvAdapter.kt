package com.sahilpc.githubbrowser.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.sahilpc.githubbrowser.R
import com.sahilpc.githubbrowser.RepoDetailActivity
import com.sahilpc.githubbrowser.models.Repo

class MainRvAdapter (private val mList: List<Repo>,private val context: Context) : RecyclerView.Adapter<MainRvAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_rv_item, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        // sets the text to the textview from our itemHolder class
        holder.repoName.text = ItemsViewModel.name
        holder.repoDes.text = ItemsViewModel.description
        holder.shareBtn.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Repository Name : ${ItemsViewModel.name} \n" +
                    "Description : ${ItemsViewModel.description} \n" +
                    "Url : ${ItemsViewModel.url}")
            context.startActivity(Intent.createChooser(shareIntent,"Share via"))
        }
        holder.itemView.setOnClickListener{
            val intent = Intent(context,RepoDetailActivity::class.java).apply {
                putExtra("repoName",ItemsViewModel.name)
                putExtra("repoDes",ItemsViewModel.description)
                putExtra("Issues",ItemsViewModel.open_issues_count.toString())
                putExtra("repoUrl",ItemsViewModel.html_url)
                putExtra("repoId", ItemsViewModel.repoId);
            }

            context.startActivity(intent)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val shareBtn: ImageButton = itemView.findViewById(R.id.shareBtn)
        val repoName: TextView = itemView.findViewById(R.id.repoName)
        val repoDes: TextView = itemView.findViewById(R.id.repoDes)
    }
}