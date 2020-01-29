package com.afterwork.mypaging.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.afterwork.mypaging.model.MainDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData
import com.afterwork.mypaging.view.paging.MainDataSourceFactory


class MainViewModel(private val model: MainDataModel): ViewModel() {

    companion object {
        val TAG = "MainViewModel"
    }

    val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: LiveData<Boolean> get() = _refreshing

    lateinit var pagedListBuilder: LivePagedListBuilder<Int, OgqContent>
//    lateinit var dataSource: DataSource<Int, OgqContent>
    val factory: MainDataSourceFactory?

    init{
        Log.d(TAG, "init")

        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(5)
            .setPrefetchDistance(5)
            .setPageSize(3)
            .build()

        factory = MainDataSourceFactory(model, _refreshing)
        pagedListBuilder = LivePagedListBuilder<Int, OgqContent>(factory, config)
    }

    fun load(key: Int): LiveData<PagedList<OgqContent>>{
        val items = pagedListBuilder.setInitialLoadKey(key).build()

        return items
    }

    fun onRefreshing(){
        Log.d(TAG, "onRefreshing")
        factory?.reset()
    }
}

