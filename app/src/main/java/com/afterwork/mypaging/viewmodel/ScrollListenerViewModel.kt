package com.afterwork.mypaging.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.network.data.Recent
import com.afterwork.mypaging.utils.NotNullMutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ScrollListenerViewModel(private val model: OgqContentDataModel): ViewModel() {
    companion object{
        val TAG = "ScrollListenerViewModel"
    }
    private val _items: NotNullMutableLiveData<List<OgqContent>> = NotNullMutableLiveData<List<OgqContent>>(mutableListOf())
    val items: LiveData<List<OgqContent>> get() = _items

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: LiveData<Boolean> get() = _refreshing

    var lastPos: String = ""

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable : Disposable){
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    init{
        Log.d(TAG, "init")
        _refreshing.value = true
        load()
    }

    fun onRefreshing() {
        Log.d(TAG, "onRefreshing")
        lastPos = ""
        load()
    }

    fun load(){
        addDisposable(getRecent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed")
                    if(lastPos.isEmpty()) {
                        _items.postValue(it.getData())
                    }else {
                        _items.postValue(_items.value.plus(it.getData()))
                    }
                    val index = it.getNext().indexOf("last_pos=")
                    lastPos = it.getNext().substring(index+"last_pos=".length)
                    _refreshing.postValue(false)
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
                _refreshing.postValue(false)
            }))
    }

    fun getRecent(): Single<Recent> {
        if(lastPos.isEmpty()) return model.getRecent()
        return model.getRecentNext(lastPos)
    }

    fun onLast(){
        if(_refreshing.value)
            return
        _refreshing.value = true
        load()
    }
}