package com.afterwork.mypaging.network.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class ImgContent(private var url: String,
                      private var type: String): BaseObservable() {
    @Bindable
    fun getUrl() = url

    @Bindable
    fun getType() = type

    fun setUrl(url: String){
        this.url = url
    }

    fun setType(type: String){
        this.type = type
    }
}