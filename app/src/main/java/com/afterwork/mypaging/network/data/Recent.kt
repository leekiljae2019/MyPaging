package com.afterwork.mypaging.network.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class Recent(private var next: String,
                  private var data: List<OgqContent>): BaseObservable(){
    @Bindable
    fun getNext() = next

    @Bindable
    fun getData() = data

    fun setNext(next: String){
        this.next = next
    }

    fun setData(data: List<OgqContent>){
        this.data = data
    }
}