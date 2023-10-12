package com.anudeep.nyt.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anudeep.nyt.app.io.Story
import com.anudeep.nyt.app.ui.DetailActivity
import com.anudeep.nyt.app.ui.NewsViewModel
import com.anudeep.nyt.app.ui.StoriesAdapter
import com.anudeep.nyt.app.util.RecyclerItemClickListener


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StoriesAdapter
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        setupRecyclerViewClickListener()
        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        // Observe LiveData from ViewModel
        viewModel.topStories.observe(this) { stories ->
            // Update your UI here
            Log.d("RESPONSE", stories.toString())
            adapter = StoriesAdapter(this, stories)
            recyclerView.adapter = adapter
            progressBar.visibility = View.GONE

        }

        // Fetch data
        viewModel.fetchTopStories()

    }

    private fun setupRecyclerViewClickListener() {
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(this, recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        val selectedStory = viewModel.storiesList[position]

                        val intent = Intent(view!!.context, DetailActivity::class.java)
                        intent.putExtra("selectedStory", selectedStory)
                        view.context.startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        // Handle long item clicks if needed
                    }
                })
        )
    }

}