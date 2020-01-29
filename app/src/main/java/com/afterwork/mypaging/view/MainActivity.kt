package com.afterwork.mypaging.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.afterwork.mypaging.R
import com.afterwork.mypaging.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "MainActivity"
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        binding.vmMain = getViewModel()
        binding.lifecycleOwner = this

        val adapter = MainViewAdapter()
        list.adapter = adapter

        binding.vmMain?.load(0)?.observe(this, Observer{
            Log.d(TAG, "CALLED binding.vmMain.load().observe()")
            adapter.submitList(it)
        })

    }
}
