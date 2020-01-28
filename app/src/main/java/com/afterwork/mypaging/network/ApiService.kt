package com.afterwork.mypaging.network

import com.afterwork.mypaging.network.data.Recent
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/v4/backgrounds/recent?format.json")
    fun getRecent(): Single<Recent>

    @GET("/api/v4/backgrounds/recent?format.json")
    fun getRecentNext(@Query("last_pos") last_pos: String): Single<Recent>
}