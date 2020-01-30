package com.afterwork.mypaging.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afterwork.mypaging.R
import com.afterwork.mypaging.view.paging.PagingActivity
import com.afterwork.mypaging.view.scrolllistener.ScrollListenerActivity
import com.afterwork.mypaging.viewmodel.paging.PagingType
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
            intent = Intent(this, PagingActivity::class.java)
            intent.putExtra(PagingActivity.PAGING_TYPE, PagingType.ITEM_KEY)
            startActivity(intent)
        }

        pageKeyPaging.setOnClickListener {
            intent = Intent(this, PagingActivity::class.java)
            intent.putExtra(PagingActivity.PAGING_TYPE, PagingType.PAGE_KEY)
            startActivity(intent)
        }

        positionalPaging.setOnClickListener {
            intent = Intent(this, PagingActivity::class.java)
            intent.putExtra(PagingActivity.PAGING_TYPE, PagingType.POSITIONAL)
            startActivity(intent)
        }
    }
}