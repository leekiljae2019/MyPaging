package com.afterwork.mypaging.view.paging.itemkey

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.network.data.Recent
import com.afterwork.mypaging.utils.NotNullMutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ItemKeyDataSource(val model: OgqContentDataModel, val refreshing: NotNullMutableLiveData<Boolean>): ItemKeyedDataSource<Int, OgqContent>(){

    companion object {
        val TAG = "ItemKeyDataSource"
    }

    var index = 0
    var keyMap: MutableMap<Int, String> = mutableMapOf(0 to "")

    override fun getKey(item: OgqContent): Int {
        Log.d(TAG, "getKey(key: ${index})")
        return index
    }

    override fun loadInitial(params: LoadInitialParams<Int>,
                            callback: LoadInitialCallback<OgqContent>) {
        Log.d(TAG, "loadInitial()")
        index += 1
        load(0, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<OgqContent>) {
        Log.d(TAG, "loadAfter(Key: ${params.key}, value: ${keyMap[params.key]})")
        index += 1
        load(params.key, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<OgqContent>) {
        Log.d(TAG, "loadBefore(Key: ${params.key})")
//        if(index > 0) {
//            index -= 1
//            load(params.key, callback)
//        }
    }

    fun load(key: Int, callback: LoadCallback<OgqContent>){
        Log.d(TAG, "load(Key: ${key}, value: ${keyMap[key]?:""})")
        refreshing.postValue(true)
        getRecent(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed")
                    val pos = it.getNext().indexOf("last_pos=")
                    val next = it.getNext().substring(pos + "last_pos=".length)
                    keyMap[index] = next
                    refreshing.postValue(false)
                    callback.onResult(it.getData())
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
                refreshing.postValue(false)
            })
    }

    fun getRecent(key: Int): Single<Recent> {
        if(key == 0) return model.getRecent()
        return model.getRecentNext(keyMap[key]?:"")
    }

    fun reset(){
        index = 0
        keyMap.clear()
        keyMap[0] = ""
        invalidate()
    }
}