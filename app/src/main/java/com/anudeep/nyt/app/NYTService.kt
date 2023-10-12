package com.anudeep.nyt.app

import com.anudeep.nyt.app.io.TopStoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTService {
    @GET("svc/topstories/v2/world.json")
    suspend fun getTopStories(@Query("api-key") apiKey: String): TopStoryResponse
}