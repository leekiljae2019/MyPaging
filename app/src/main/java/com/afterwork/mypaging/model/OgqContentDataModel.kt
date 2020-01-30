package com.afterwork.mypaging.model

import com.afterwork.mypaging.network.data.Recent
import io.reactivex.Single

interface OgqContentDataModel {
    fun getRecent(): Single<Recent>
    fun getRecentNext(last_pos: String): Single<Recent>
}