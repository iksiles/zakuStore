package com.fz.zakustore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fz.zakustore.R
import com.fz.zakustore.zaku

class zakuAdapter(private val zakulist:List<zaku>) : RecyclerView.Adapter<zakuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): zakuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return zakuViewHolder(layoutInflater.inflate(R.layout.item_zaku, parent, false))
    }

    override fun getItemCount(): Int {
        return zakulist.size
    }

    override fun onBindViewHolder(holder: zakuViewHolder, position: Int) {
        val item = zakulist[position]
        holder.render(item)
    }
}