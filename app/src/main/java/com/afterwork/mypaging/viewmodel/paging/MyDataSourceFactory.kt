package com.afterwork.mypaging.viewmodel.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData

enum class PagingType {ITEM_KEY, PAGE_KEY, POSITIONAL}

class MyDataSourceFactory(val type: PagingType, val model: OgqContentDataModel, val refreshing: NotNullMutableLiveData<Boolean>): DataSource.Factory<Int, OgqContent>(){
    companion object {
        val TAG = "MyDataSourceFactory"
    }

    lateinit var sourceLiveData: Any
    lateinit var dataSource: Any


    override fun create(): DataSource<Int, OgqContent> {
        when(type){
            PagingType.ITEM_KEY -> return createItemKey()
            PagingType.PAGE_KEY -> return createPageKey()
            else -> return createPositional()
        }
    }

    fun reset(){
        when(type){
            PagingType.ITEM_KEY -> (dataSource as ItemKeyDataSource)?.reset()
            PagingType.PAGE_KEY -> (dataSource as PageKeyDataSource)?.reset()
            else -> (dataSource as PositionalDataSource)?.reset()
        }
    }

    private fun createItemKey(): DataSource<Int, OgqContent>{
        sourceLiveData = MutableLiveData<ItemKeyDataSource>()
        dataSource = ItemKeyDataSource(model, refreshing)
        (sourceLiveData as MutableLiveData<ItemKeyDataSource>).postValue((dataSource as ItemKeyDataSource))
        return dataSource!! as ItemKeyDataSource
    }

    private fun createPageKey(): DataSource<Int, OgqContent>{
        sourceLiveData = MutableLiveData<PageKeyDataSource>()
        dataSource = PageKeyDataSource(model, refreshing)
        (sourceLiveData as MutableLiveData<PageKeyDataSource>).postValue((dataSource as PageKeyDataSource))
        return dataSource as PageKeyDataSource
    }

    private fun createPositional(): DataSource<Int, OgqContent>{
        sourceLiveData = MutableLiveData<PositionalDataSource>()
        dataSource = PositionalDataSource(model, refreshing)
        (sourceLiveData as MutableLiveData<PositionalDataSource>).postValue((dataSource as PositionalDataSource))
        return dataSource as PositionalDataSource
    }

}