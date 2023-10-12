package com.anudeep.nyt.app.io

import com.anudeep.nyt.app.NYTService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitClient {
    private const val BASE_URL = "https://api.nytimes.com/"

    private val jsonFormat = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonFormat.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val nytService: NYTService by lazy {
        retrofit.create(NYTService::class.java)
    }
}
