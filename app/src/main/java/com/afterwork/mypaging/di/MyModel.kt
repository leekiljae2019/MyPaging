package com.afterwork.mypaging.di

import com.afterwork.mypaging.viewmodel.ItemKeyPagingViewModel
import com.afterwork.mypaging.model.OgqContentDataModel
import com.afterwork.mypaging.model.OgqContentDataModelImpl
import com.afterwork.mypaging.network.ApiService
import com.afterwork.mypaging.viewmodel.PageKeyPagingViewModel
import com.afterwork.mypaging.viewmodel.PositionalPagingViewModel
import com.afterwork.mypaging.viewmodel.ScrollListenerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val API_BASE_URL = "http://bgh.ogqcorp.com"

var modelPart = module {
    factory<OgqContentDataModel> {
        OgqContentDataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel {
        ScrollListenerViewModel(get())
    }

    viewModel{
        ItemKeyPagingViewModel(get())
    }

    viewModel{
        PageKeyPagingViewModel(get())
    }

    viewModel{
        PositionalPagingViewModel(get())
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
