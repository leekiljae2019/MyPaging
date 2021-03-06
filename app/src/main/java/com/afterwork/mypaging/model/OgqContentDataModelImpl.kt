package com.afterwork.mypaging.model

import com.afterwork.mypaging.network.ApiService
import com.afterwork.mypaging.network.data.Recent
import io.reactivex.Single

class OgqContentDataModelImpl(private val service: ApiService): OgqContentDataModel {
    override fun getRecent(): Single<Recent> {
        return service.getRecent()
    }

    override fun getRecentNext(last_pos: String): Single<Recent> {
        return service.getRecentNext(last_pos)
    }
}