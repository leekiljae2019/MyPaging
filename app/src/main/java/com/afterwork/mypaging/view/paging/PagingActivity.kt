package com.afterwork.mypaging.view.paging

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.afterwork.mypaging.R
import com.afterwork.mypaging.databinding.ActivityPagingBinding
import com.afterwork.mypaging.viewmodel.paging.PagingType
import kotlinx.android.synthetic.main.activity_paging.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class PagingActivity: BaseActivity<ActivityPagingBinding>(){
    companion object{
        val TAG = "PagingActivity"
        val PAGING_TYPE = "type"
    }

    override val layoutResourceId: Int
        get() = R.layout.activity_paging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var pagingType: PagingType = intent.getSerializableExtra(PAGING_TYPE) as PagingType
        binding.vmPaging = getViewModel{ parametersOf(pagingType) }
        binding.lifecycleOwner = this

        val adapter = MyPagingAdapter()
        list.adapter = adapter

        binding.vmPaging?.load(0)?.observe(this, Observer{
            Log.d(TAG, "CALLED binding.vmMain.load().observe()")
            adapter.submitList(it)
        })

    }
}
