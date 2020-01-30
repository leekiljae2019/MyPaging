package com.afterwork.mypaging.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData
import com.afterwork.mypaging.view.paging.positional.PositionalDataSourceFactory

class PositionalPagingViewModel(private val model: OgqContentDataModel): ViewModel() {

    companion object {
        val TAG = "PositionalViewModel"
    }

    val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: LiveData<Boolean> get() = _refreshing

    var pagedListBuilder: LivePagedListBuilder<Int, OgqContent>

    val factory: PositionalDataSourceFactory?

    init{
        Log.d(TAG, "init")

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(40)
            .setPrefetchDistance(3)
            .setPageSize(40)
            .build()

        factory = PositionalDataSourceFactory(model, _refreshing)
        pagedListBuilder = LivePagedListBuilder<Int, OgqContent>(factory, config)
    }

    fun load(key: Int) = pagedListBuilder.setInitialLoadKey(key).build()

    fun onRefreshing(){
        Log.d(TAG, "onRefreshing")
        factory?.reset()
    }
}