package com.afterwork.mypaging.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afterwork.mypaging.R
import com.afterwork.mypaging.databinding.ItemOgqcontentBinding
import com.afterwork.mypaging.network.data.OgqContent
import com.afterwork.mypaging.viewmodel.MainViewModel


class MainViewAdapter(var items: List<OgqContent> = arrayListOf(), val vm: MainViewModel)
        : RecyclerView.Adapter<MainViewAdapter.MainViewHolder>() {

    companion object{
        val TAG = "MainViewAdapter"
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

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder(position: $position)")
        holder.binding.item = items[position]
        holder.binding.vmMain = vm
    }

}