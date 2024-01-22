package com.fz.zakustore.dbAdapter

import android.content.ClipData.Item
import android.content.Intent
import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.fz.zakustore.MainActivity
import com.fz.zakustore.R
import com.fz.zakustore.Update
import com.fz.zakustore.database.dao.zakuDao
import com.fz.zakustore.database.entities.zakuEntity
import com.fz.zakustore.database.zakuDatabase
import com.fz.zakustore.db
import com.fz.zakustore.zaku
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class zakuDbAdapter(private val dbList: List<zakuEntity>): RecyclerView.Adapter<zakuDbViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): zakuDbViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return zakuDbViewHolder(layoutInflater.inflate(R.layout.item_zaku, parent, false))
    }

    override fun getItemCount(): Int = dbList.size

    override fun onBindViewHolder(holder: zakuDbViewHolder, position: Int) {
        val item = dbList[position]
        holder.render(item)
        holder.delete.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                db = Room.databaseBuilder(holder.model.context, zakuDatabase::class.java, "zakuStore_db").build()
                db.zakuDao().delete(item)
                val intent = Intent(holder.model.context, MainActivity::class.java)
                holder.model.context.startActivity(intent)
            }
        }
        holder.update.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                db = Room.databaseBuilder(holder.model.context, zakuDatabase::class.java, "zakuStore_db").build()
                val intent = Intent(holder.model.context, Update::class.java)
                val bundle = Bundle()
                bundle.putInt("zaku", item.id)
                intent.putExtra("bundle", bundle)
                holder.model.context.startActivity(intent)
            }
        }
    }
}