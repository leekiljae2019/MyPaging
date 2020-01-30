package com.afterwork.mypaging.view.paging

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.afterwork.mypaging.R
import com.afterwork.mypaging.databinding.ActivityPositionalpagingBinding
import com.afterwork.mypaging.view.paging.common.BaseActivity
import com.afterwork.mypaging.view.paging.common.MyPagingAdapter
import kotlinx.android.synthetic.main.activity_positionalpaging.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class PositionalPagingActivity: BaseActivity<ActivityPositionalpagingBinding>(){

    companion object{
        val TAG = "PositionalActivity"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_positionalpaging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vmPos = getViewModel()
        binding.lifecycleOwner = this

        val adapter = MyPagingAdapter()
        list.adapter = adapter

        binding.vmPos?.load(0)?.observe(this, Observer{
            Log.d(TAG, "CALLED binding.vmMain.load().observe()")
            adapter.submitList(it)
        })
    }
}
