package com.afterwork.mypaging.view.paging.itemkey

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData


class ItemKeyDataSourceFactory(val model: OgqContentDataModel, val refreshing: NotNullMutableLiveData<Boolean>): DataSource.Factory<Int, OgqContent>(){
    val sourceLiveData = MutableLiveData<ItemKeyDataSource>()
    var dataSource: ItemKeyDataSource? = null

    override fun create(): DataSource<Int, OgqContent> {
        dataSource = ItemKeyDataSource(model, refreshing)
        sourceLiveData.postValue(dataSource)
        return dataSource as ItemKeyDataSource
    }

    fun reset(){
        dataSource?.reset()
    }

}
