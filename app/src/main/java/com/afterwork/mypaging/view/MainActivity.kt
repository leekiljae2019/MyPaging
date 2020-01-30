package com.afterwork.mypaging.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afterwork.mypaging.R
import com.afterwork.mypaging.view.paging.ItemKeyPagingActivity
import com.afterwork.mypaging.view.paging.PageKeyPagingActivity
import com.afterwork.mypaging.view.paging.PositionalPagingActivity
import com.afterwork.mypaging.view.scrolllistener.ScrollListenerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    companion object{
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollListener.setOnClickListener {
            startActivity(Intent(this, ScrollListenerActivity::class.java))
        }

        itemKeyPaging.setOnClickListener {
            startActivity(Intent(this, ItemKeyPagingActivity::class.java))
        }

        pageKeyPaging.setOnClickListener {
            startActivity(Intent(this, PageKeyPagingActivity::class.java))
        }

        positionalPaging.setOnClickListener {
            startActivity(Intent(this, PositionalPagingActivity::class.java))
        }
    }
}