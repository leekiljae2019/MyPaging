package com.afterwork.mypaging.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData
import com.afterwork.mypaging.viewmodel.paging.MyDataSourceFactory
import com.afterwork.mypaging.viewmodel.paging.PagingType

abstract class BaseViewModel<B: Any>(private val pagingType: PagingType, private val model: OgqContentDataModel): ViewModel() {

    companion object {
        val TAG = "ItemKeyPagingViewModel"
    }

    val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: LiveData<Boolean> get() = _refreshing

    lateinit var pagedListBuilder: B
    var factory: MyDataSourceFactory

    init{
        Log.d(TAG, "init")

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(40)
            .setPrefetchDistance(3)
            .setPageSize(40)
            .build()

        factory = MyDataSourceFactory(pagingType, model, _refreshing)

        initViewModel(config)
    }

    abstract protected fun initViewModel(config: PagedList.Config)

    abstract fun load(key: Int): LiveData<PagedList<OgqContent>>

    fun onRefreshing(){
        factory.reset()
    }
}
