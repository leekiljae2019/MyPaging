package com.afterwork.mypaging.utils

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.afterwork.mypaging.MainViewAdapter
import com.afterwork.mypaging.MainViewModel
import com.afterwork.mypaging.network.data.ImgContent
import com.afterwork.mypaging.network.data.OgqContent
import com.facebook.drawee.view.SimpleDraweeView

@BindingAdapter("thumbnailImage")
fun thumbnailImage(view: SimpleDraweeView, images: List<ImgContent>){

    for(img in images) {
        if ("thumbnail".equals(img.getType())) {
            view.setImageURI(img.getUrl())
            break;
        }
    }
}

@BindingAdapter("emptyTitle")
fun emptyTitle(view: TextView, title: String){
    if(title.isEmpty() || ".".equals(title)){
        view.setText("UNTITLE")
    }else {
        view.setText(title)
    }
}

@BindingAdapter(value = ["views", "likes", "downloads"], requireAll = true)
fun textInfo(view: TextView, views: Int, likes: Int, downloads: Int){
    view.setText("Views: $views, Likes: $likes, Downloads: $downloads")
}

@BindingAdapter(value = ["ogqContents", "viewModel"], requireAll = true)
fun setMainViewAdapter(view: RecyclerView, items: List<OgqContent>, vm: MainViewModel) {
    Log.d("mainViewAdapter", "mainViewAdapter")

    view.adapter?.run {
        if (this is MainViewAdapter) {
            Log.d("mainViewAdapter", "item size: ${items.size}")
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        Log.d("mainViewAdapter", "MainViewAdapter create")
        MainViewAdapter(items, vm).apply { view.adapter = this }
    }
}

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(_visible: Boolean) {
    Log.d("SwipeRefreshLayout", "refreshing : $_visible")
    isRefreshing = _visible
}
