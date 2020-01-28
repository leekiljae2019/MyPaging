package com.afterwork.mypaging

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afterwork.mypaging.model.MainDataModel
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.network.data.Recent
import com.afterwork.mypaging.utils.NotNullMutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainViewModel(private val model: MainDataModel): ViewModel() {

    companion object {
        val TAG = "MainViewModel"
    }

//    private val _recent: MutableLiveData<Recent> = MutableLiveData()
//    val recent: LiveData<Recent> get() = _recent

    private val _items: NotNullMutableLiveData<List<OgqContent>> = NotNullMutableLiveData<List<OgqContent>>(mutableListOf())
    val items: LiveData<List<OgqContent>> get() = _items

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: LiveData<Boolean> get() = _refreshing

//    val refreshing: ObservableBoolean = ObservableBoolean(false)


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
//        refreshing.set(true)
        onRefreshing()
    }

    fun onRefreshing(){
        Log.d(TAG, "onRefreshing")

        addDisposable(model.getRecent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    Log.d(TAG, "Successed")
//                    _recent.postValue(it)
                    _items.postValue(_items.value.plus(it.getData()))
//                    _items.postValue(it.getData())
                    _refreshing.postValue(false)
//                    refreshing.set(false)
                }
            }, {
                Log.d(TAG, "Failed: ${it.localizedMessage}")
                _refreshing.postValue(false)
//                refreshing.set(false)
            }))
    }

}