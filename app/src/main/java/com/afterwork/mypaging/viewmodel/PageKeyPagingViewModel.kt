package com.afterwork.mypaging.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData
import com.afterwork.mypaging.view.paging.pagekey.PageKeyDataSourceFactory

class PageKeyPagingViewModel(private val model: OgqContentDataModel): ViewModel(){
    companion object {
        val TAG = "PageKeyPagingViewModel"
    }

    val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: LiveData<Boolean> get() = _refreshing

    var pagedListBuilder: LivePagedListBuilder<String, OgqContent>

    val factory: PageKeyDataSourceFactory?

    init{
        Log.d(TAG, "init")

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(40)
            .setPrefetchDistance(3)
            .setPageSize(40)
            .build()

        factory = PageKeyDataSourceFactory(model, _refreshing)
        pagedListBuilder = LivePagedListBuilder<String, OgqContent>(factory, config)
    }

    fun load(key: String) = pagedListBuilder.setInitialLoadKey(key).build()

    fun onRefreshing(){
        Log.d(TAG, "onRefreshing")
        factory?.reset()
    }
}