package com.anudeep.nyt.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anudeep.nyt.app.io.RetrofitClient.nytService
import com.anudeep.nyt.app.io.Story
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val _topStories = MutableLiveData<List<Story>>()
    val topStories: LiveData<List<Story>> = _topStories
    val storiesList: ArrayList<Story> = ArrayList()
    fun fetchTopStories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = nytService.getTopStories("AqXElC0Gkpxu5R7WwPLlt4YGXFPbZbdv")
                storiesList.clear()
                storiesList.addAll(response.results)
                _topStories.postValue(storiesList)
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle exceptions or post errors to LiveData
            }
        }
    }
}
