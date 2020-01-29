package com.afterwork.mypaging.view.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.afterwork.mypaging.model.MainDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData


class MainDataSourceFactory(val model: MainDataModel, val refreshing: NotNullMutableLiveData<Boolean>): DataSource.Factory<Int, OgqContent>(){
    val sourceLiveData = MutableLiveData<MainDataSource>()
    var dataSource: MainDataSource? = null

    override fun create(): DataSource<Int, OgqContent> {
        dataSource = MainDataSource(model, refreshing)
        sourceLiveData.postValue(dataSource)
        return dataSource as MainDataSource
    }

    fun reset(){
        dataSource?.reset()
    }

}
