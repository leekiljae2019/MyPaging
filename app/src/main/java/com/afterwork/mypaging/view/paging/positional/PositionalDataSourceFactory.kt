package com.afterwork.mypaging.view.paging.positional

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData

class PositionalDataSourceFactory (val model: OgqContentDataModel, val refreshing: NotNullMutableLiveData<Boolean>): DataSource.Factory<Int, OgqContent>() {
    val sourceLiveData = MutableLiveData<PositionalDataSource>()
    var dataSource: PositionalDataSource? = null

    override fun create(): DataSource<Int, OgqContent> {
        dataSource = PositionalDataSource(model, refreshing)
        sourceLiveData.postValue(dataSource)
        return dataSource as PositionalDataSource
    }

    fun reset() {
        dataSource?.reset()
    }
}