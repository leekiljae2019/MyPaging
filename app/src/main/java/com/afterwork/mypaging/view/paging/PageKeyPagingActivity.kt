package com.afterwork.mypaging.view.paging

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.afterwork.mypaging.R
import com.afterwork.mypaging.databinding.ActivityPagekeypagingBinding
import com.afterwork.mypaging.view.paging.common.BaseActivity
import com.afterwork.mypaging.view.paging.common.MyPagingAdapter
import kotlinx.android.synthetic.main.activity_pagekeypaging.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class PageKeyPagingActivity: BaseActivity<ActivityPagekeypagingBinding>(){
    companion object {
        val TAG = "PageKeyPagingActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_pagekeypaging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vmPageKey = getViewModel()
        binding.lifecycleOwner = this

        val adapter = MyPagingAdapter()
        list.adapter = adapter

        binding.vmPageKey?.load("")?.observe(this, Observer{
            Log.d(TAG, "CALLED binding.vmMain.load().observe()")
            adapter.submitList(it)
        })

    }
}