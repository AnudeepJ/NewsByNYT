package com.anudeep.nyt.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.anudeep.nyt.app.R
import com.anudeep.nyt.app.io.Story
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Find the Toolbar in the layout
        val toolbar = findViewById<Toolbar>(R.id.detail_toolbar)

        // Set the Toolbar as the support action bar
        setSupportActionBar(toolbar)

        // Enable the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Retrieve the selected story from the Intent
        val selectedStory = intent.getSerializableExtra("selectedStory") as Story
        supportActionBar?.title = selectedStory.title
        val drawable = ContextCompat.getDrawable(this, R.drawable.arrow_white)
        supportActionBar?.setHomeAsUpIndicator(drawable)

        // Populate UI elements with story information
        val titleTextView = findViewById<TextView>(R.id.detail_titleTextView)
        val abstractTextView = findViewById<TextView>(R.id.detail_abstractTextView)
        val bylineTextView = findViewById<TextView>(R.id.detail_bylineTextView)
        val publishedDateTextView = findViewById<TextView>(R.id.detail_publishedDateTextView)
        val imageView = findViewById<ImageView>(R.id.detail_imageView) // Find the ImageView

        // Set data from the selected story
        titleTextView.text = selectedStory.title
        abstractTextView.text = selectedStory.abstract
        bylineTextView.text = selectedStory.byline
        publishedDateTextView.text = selectedStory.published_date
        Glide.with(this)
            .load(selectedStory.multimedia?.get(0)?.url) // Replace with the actual image URL field from your Story
            .into(imageView)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Handle the back button click
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}