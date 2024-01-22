package com.fz.zakustore.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fz.zakustore.R
import com.fz.zakustore.zaku

class zakuViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val model = view.findViewById<TextView>(R.id.tvZakuModel)
    val nick = view.findViewById<TextView>(R.id.tvZakuNick)
    val photo = view.findViewById<ImageView>(R.id.ivZakuPhoto)

    fun render(zakuModel: zaku) {
        model.text = zakuModel.model
        nick.text = zakuModel.nick
        Glide.with(photo.context).load(zakuModel.photo).into(photo)
    }
}