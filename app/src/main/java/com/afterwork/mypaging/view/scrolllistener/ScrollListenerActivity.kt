package com.afterwork.mypaging.view.scrolllistener

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.afterwork.mypaging.R
import com.afterwork.mypaging.databinding.ActivityScrolllistenerBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ScrollListenerActivity: AppCompatActivity() {
    companion object{
        val TAG = "ScrollListenerActivity"
    }

    lateinit var binding: ActivityScrolllistenerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_scrolllistener
        )
        binding.vmScroll = getViewModel()
        binding.lifecycleOwner = this
    }
}