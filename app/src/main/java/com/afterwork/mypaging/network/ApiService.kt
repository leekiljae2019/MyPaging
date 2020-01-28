package com.afterwork.mypaging.network

import com.afterwork.mypaging.network.data.Recent
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("/api/v4/backgrounds/recent?format.json")
    fun getRecent(): Single<Recent>
}