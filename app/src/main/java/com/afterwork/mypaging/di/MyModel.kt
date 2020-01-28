package com.afterwork.mypaging.di

import com.afterwork.mypaging.viewmodel.MainViewModel
import com.afterwork.mypaging.model.MainDataModel
import com.afterwork.mypaging.model.MainDataModelImpl
import com.afterwork.mypaging.network.ApiService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val API_BASE_URL = "http://bgh.ogqcorp.com"

var modelPart = module {
    factory<MainDataModel> {
        MainDataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel {
        MainViewModel(get())
    }
}

var retrofitPart = module {
    single<ApiService>{
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

var myDiModule = listOf(
    modelPart,
    viewModelPart,
    retrofitPart
)
