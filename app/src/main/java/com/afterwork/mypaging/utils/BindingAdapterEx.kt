package com.afterwork.mypaging.utils

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.afterwork.mypaging.view.MainViewAdapter
import com.afterwork.mypaging.viewmodel.MainViewModel
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

//@BindingAdapter(value = ["viewModel", "ogqContents"], requireAll = true)
//fun setMainViewAdapter(view: RecyclerView, vm: MainViewModel, items: PagedList<OgqContent>) {
//    Log.d("mainViewAdapter", "mainViewAdapter")
//
//    view.adapter?.run {
//        if (this is MainViewAdapter) {
//            Log.d("mainViewAdapter", "MainViewAdapter already created")
//            this.notifyDataSetChanged()
//        }
//    } ?: run {
//        Log.d("mainViewAdapter", "MainViewAdapter create")
//        MainViewAdapter().apply { view.adapter = this }
//        val adapter = view.adapter as MainViewAdapter
//        adapter.submitList(items)
//    }

//    view.setOnScrollChangeListener(View.OnScrollChangeListener { view, i, i2, i3, i4 ->
//        Log.i(MainViewModel.TAG, "onScrollStateChanged()")
//        if (!view.canScrollVertically(-1)) {
//            Log.i("BindingAdapterEx", "Top of list");
//        } else if (!view.canScrollVertically(1)) {
//            Log.i("BindingAdapterEx", "End of list");
//            vm.onLast()
//        }
//    })
//}

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(_visible: Boolean) {
    Log.d("SwipeRefreshLayout", "refreshing : $_visible")
    isRefreshing = _visible
}
