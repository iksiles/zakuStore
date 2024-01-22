package com.fz.zakustore.dbAdapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fz.zakustore.R
import com.fz.zakustore.database.entities.zakuEntity

class zakuDbViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val model = view.findViewById<TextView>(R.id.tvZakuModel)
    val nick = view.findViewById<TextView>(R.id.tvZakuNick)
    val photo = view.findViewById<ImageView>(R.id.ivZakuPhoto)
    val update = view.findViewById<Button>(R.id.btZakuUpdate)
    val delete = view.findViewById<Button>(R.id.btZakuDelete)

    fun render(zakuModel: zakuEntity) {
        model.text = zakuModel.model
        nick.text = zakuModel.nick
        Glide.with(photo.context).load(zakuModel.photo).into(photo)
    }
}