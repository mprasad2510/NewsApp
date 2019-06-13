package org.mp.newsapp.di.feature.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import kotlinx.android.synthetic.main.news_list_items.view.headlineTv
import org.mp.newsapp.R


class NewsAdapter(val dataSource: List<Int>, private val share:(Int)->Unit) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_list_items, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(dataSource[position]){
            holder.headline?.text = dataSource[position].toString()
            holder.item.setOnClickListener { share.invoke(this) }
        }

    }

    override fun getItemCount(): Int {
        return dataSource.size
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val item = view
    val headline = view.headlineTv
}