package com.afterwork.mypaging.viewmodel.paging

import androidx.paging.DataSource
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData

enum class PagingType {ITEM_KEY, PAGE_KEY, POSITIONAL}

class MyDataSourceFactory(val type: PagingType, val model: OgqContentDataModel, val refreshing: NotNullMutableLiveData<Boolean>): DataSource.Factory<Int, OgqContent>(){
    companion object {
        val TAG = "MyDataSourceFactory"
    }

    lateinit var dataSource: BaseDataSource

    override fun create(): DataSource<Int, OgqContent> {
        when (type) {
            PagingType.ITEM_KEY -> dataSource = ItemKeyDataSource(model, refreshing)
            PagingType.PAGE_KEY -> dataSource = PageKeyDataSource(model, refreshing)
            else -> dataSource = PositionaalDataSource(model, refreshing)
        }
        dataSource.init()

        return dataSource.get()
    }
}