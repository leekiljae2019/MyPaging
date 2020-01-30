package com.afterwork.mypaging.viewmodel.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PageKeyDataSource(val model: OgqContentDataModel, val refreshing: NotNullMutableLiveData<Boolean>): PageKeyedDataSource<Int, OgqContent>() {

    companion object {
        val TAG = "PageKeyDataSource"
    }

    var next = ""

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, OgqContent>) {
        Log.d(TAG, "loadInitial()")

        refreshing.postValue(true)
        model.getRecent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed")
                    val pos = it.getNext().indexOf("last_pos=")
                    next = it.getNext().substring(pos + "last_pos=".length)
                    refreshing.postValue(false)
                    callback.onResult(it.getData(), null, 1)
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
                refreshing.postValue(false)
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, OgqContent>) {
        Log.d(TAG, "loadAfter(Key: ${params.key})")
        load(params.key, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, OgqContent>) {
        Log.d(TAG, "loadBefore(Key: ${params.key})")
    }

    fun load(key: Int, callback: LoadCallback<Int, OgqContent>){
        Log.d(TAG, "load(Key: ${key})")
        refreshing.postValue(true)
        model.getRecentNext(next)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed")
                    val pos = it.getNext().indexOf("last_pos=")
                    next = it.getNext().substring(pos + "last_pos=".length)
                    refreshing.postValue(false)
                    callback.onResult(it.getData(), key+1)
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
                refreshing.postValue(false)
            })
    }

    fun reset(){
        invalidate()
    }
}