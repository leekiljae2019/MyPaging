package com.afterwork.mypaging.view.paging

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.afterwork.mypaging.R
import com.afterwork.mypaging.databinding.ActivityItemkeypagingBinding
import com.afterwork.mypaging.view.paging.common.BaseActivity
import com.afterwork.mypaging.view.paging.common.MyPagingAdapter
import kotlinx.android.synthetic.main.activity_itemkeypaging.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ItemKeyPagingActivity: BaseActivity<ActivityItemkeypagingBinding>() {
    companion object {
        val TAG = "ItemKeyPagingActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_itemkeypaging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vmItemKey = getViewModel()
        binding.lifecycleOwner = this

        val adapter = MyPagingAdapter()
        list.adapter = adapter

        binding.vmItemKey?.load(0)?.observe(this, Observer{
            Log.d(TAG, "CALLED binding.vmMain.load().observe()")
            adapter.submitList(it)
        })

    }
}
