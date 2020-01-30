package com.afterwork.mypaging.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.viewmodel.paging.PagingType

class PagingViewModel(private val pagingType: PagingType, private val model: OgqContentDataModel): BaseViewModel<LivePagedListBuilder<Int, OgqContent>>(pagingType, model){

    override fun initViewModel(config: PagedList.Config) {
        pagedListBuilder = LivePagedListBuilder<Int, OgqContent>(factory, config)
    }

    override fun load(key: Int): LiveData<PagedList<OgqContent>> {
        return pagedListBuilder.setInitialLoadKey(key).build()
    }

}