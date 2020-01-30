package com.afterwork.mypaging.view.paging.pagekey

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PageKeyDataSource(val model: OgqContentDataModel, val refreshing: NotNullMutableLiveData<Boolean>): PageKeyedDataSource<String, OgqContent>() {

    companion object {
        val TAG = "PageKeyDataSource"
    }

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, OgqContent>) {
        Log.d(TAG, "loadInitial()")

        refreshing.postValue(true)
        model.getRecent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed")
                    val pos = it.getNext().indexOf("last_pos=")
                    val next = it.getNext().substring(pos + "last_pos=".length)
                    refreshing.postValue(false)
                    callback.onResult(it.getData(), null, next)
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
                refreshing.postValue(false)
            })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, OgqContent>) {
        Log.d(TAG, "loadAfter(Key: ${params.key})")
        load(params.key, callback)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, OgqContent>) {
        Log.d(TAG, "loadBefore(Key: ${params.key})")
    }

    fun load(key: String, callback: LoadCallback<String, OgqContent>){
        Log.d(TAG, "load(Key: ${key})")
        refreshing.postValue(true)
        model.getRecentNext(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed")
                    val pos = it.getNext().indexOf("last_pos=")
                    val next = it.getNext().substring(pos + "last_pos=".length)
                    refreshing.postValue(false)
                    callback.onResult(it.getData(), next)
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