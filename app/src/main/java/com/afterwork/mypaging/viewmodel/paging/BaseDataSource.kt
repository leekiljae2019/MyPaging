package com.afterwork.mypaging.viewmodel.paging

import androidx.paging.DataSource
import com.afterwork.mypaging.network.data.OgqContent

interface BaseDataSource{

    fun init()

    fun get(): DataSource<Int, OgqContent>

    fun reset()
}
