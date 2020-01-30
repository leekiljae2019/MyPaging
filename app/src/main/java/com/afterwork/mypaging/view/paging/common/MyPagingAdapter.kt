package com.afterwork.mypaging.view.paging.common

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.afterwork.mypaging.R
import com.afterwork.mypaging.databinding.ItemOgqcontentBinding
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.view.BindingViewHolder

class MyPagingAdapter: PagedListAdapter<OgqContent, MyPagingAdapter.MainViewHolder>(
    DIFF_CALLBACK
){

    companion object{
        val TAG = "MyPagingAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<OgqContent>() {
            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: OgqContent, newItem: OgqContent) =
                oldItem.getId() == newItem.getId()

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: OgqContent, newItem: OgqContent) = oldItem == newItem
        }
    }

    class MainViewHolder(view: View) : BindingViewHolder<ItemOgqcontentBinding>(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        Log.d(TAG, "onCreateViewHolder(viewType: $viewType)")
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_ogqcontent,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder(position: $position)")
        holder.binding.item = getItem(position)
    }
}