package com.afterwork.mypaging.model

import com.afterwork.mypaging.network.data.Recent
import io.reactivex.Single

interface MainDataModel {
    fun getRecent(): Single<Recent>
}