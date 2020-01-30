package com.afterwork.mypaging.view.paging.pagekey

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData

class PageKeyDataSourceFactory (val model: OgqContentDataModel, val refreshing: NotNullMutableLiveData<Boolean>): DataSource.Factory<String, OgqContent>(){
    val sourceLiveData = MutableLiveData<PageKeyDataSource>()
    var dataSource: PageKeyDataSource? = null

    override fun create(): DataSource<String, OgqContent> {
        dataSource = PageKeyDataSource(model, refreshing)
        sourceLiveData.postValue(dataSource)
        return dataSource as PageKeyDataSource
    }

    fun reset(){
        dataSource?.reset()
    }
}