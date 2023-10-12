package com.anudeep.nyt.app.ui
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anudeep.nyt.app.R
import com.anudeep.nyt.app.io.Story
import com.bumptech.glide.Glide

class StoriesAdapter(private val context: Context, private val storiesList: List<Story>) : RecyclerView.Adapter<StoriesAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.story_image)
        val titleTextView: TextView = view.findViewById(R.id.title_text_view)
        val abstractTextView: TextView = view.findViewById(R.id.abstract_text_view)
        val bylineTextView: TextView = view.findViewById(R.id.byline_text_view)
        val publishedDateTextView: TextView = view.findViewById(R.id.published_date_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.story_list_item, parent, false)
        return StoryViewHolder(itemView)
    }

    override fun getItemCount(): Int = storiesList.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = storiesList[position]
        holder.titleTextView.text = story.title
        holder.abstractTextView.text = story.abstract
        holder.bylineTextView.text = story.byline
        holder.publishedDateTextView.text = "Published on: ${story.published_date}"

        // Assuming each story has a multimedia list and you want to load the first image (if available)
        if (story.multimedia?.isNotEmpty() == true) {
            Glide.with(context).load(story.multimedia!![0].url).into(holder.imageView)
        }
    }
}
