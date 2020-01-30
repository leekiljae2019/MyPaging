package com.afterwork.mypaging.view.paging.positional

import android.util.Log
import androidx.paging.PositionalDataSource
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.utils.NotNullMutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PositionalDataSource(val model: OgqContentDataModel, val refreshing: NotNullMutableLiveData<Boolean>): PositionalDataSource<OgqContent>() {

    companion object {
        val TAG = "PageKeyDataSource"
    }

    var next: String = ""

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<OgqContent>) {
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
                    callback.onResult(it.getData(), 0)
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
                refreshing.postValue(false)
            })
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<OgqContent>) {
        load(next, callback)
    }

    fun load(key: String, callback: LoadRangeCallback<OgqContent>){
        Log.d(TAG, "load(Key: ${key})")
        refreshing.postValue(true)
        model.getRecentNext(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed")
                    val pos = it.getNext().indexOf("last_pos=")
                    next = it.getNext().substring(pos + "last_pos=".length)
                    refreshing.postValue(false)
                    callback.onResult(it.getData())
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