package com.fz.zakustore

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.fz.zakustore.database.zakuDatabase
import kotlinx.coroutines.launch

lateinit var db: zakuDatabase

class Fragment_Toolbar : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment__toolbar, container, false)
        val toolbar : Toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_home -> {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_insert -> {
                val intent = Intent(activity, Insert::class.java)
                startActivity(intent)
                true
            }
            R.id.action_delete -> {
                db = Room.databaseBuilder(requireActivity(), zakuDatabase::class.java, "zakuStore_db").allowMainThreadQueries().build()
                lifecycleScope.launch {
                db.zakuDao().deleteAllZakus()
                }
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}