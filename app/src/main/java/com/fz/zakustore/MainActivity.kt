package com.fz.zakustore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.fz.zakustore.adapter.zakuAdapter
import com.fz.zakustore.database.entities.teamEntity
import com.fz.zakustore.database.entities.zakuEntity
import com.fz.zakustore.database.zakuDatabase
import com.fz.zakustore.dbAdapter.zakuDbAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(this, zakuDatabase::class.java, "zakuStore_db").fallbackToDestructiveMigration().build()
        lifecycleScope.launch {
            val zakus = listOf<zakuEntity>(
                zakuEntity(1,"MS-05B", "Zaku I", 1, "https://static.wikia.nocookie.net/gundam/images/3/35/MS05B_Zaku_I_-_Front.jpg/revision/latest?cb=20111115230150"),
                zakuEntity(2,"MS-06F", "Zaku II", 1, "https://static.wikia.nocookie.net/gundam/images/8/8d/Ms-06f.jpg/revision/latest?cb=20140902160701"),
                zakuEntity(3,"MS-07B", "Gouf", 1, "https://static.wikia.nocookie.net/gundam/images/d/d1/Ms-07b_1.jpg/revision/latest/scale-to-width-down/1000?cb=20201101133303")
            )
            db.zakuDao().insertAll(zakus)
        }
/*        lifecycleScope.launch {
            db.teamDao().insertTeam(teamEntity(0))
            db.teamDao().insertTeam(teamEntity(1))
        }*/
/*        lifecycleScope.launch {
            db.zakuDao().insertZakuAndTeam(zakuEntity(1,"MS-05B", "Zaku I", 1, "https://static.wikia.nocookie.net/gundam/images/3/35/MS05B_Zaku_I_-_Front.jpg/revision/latest?cb=20111115230150"),
                teamEntity(1,1)
            )
        }*/
        initRecyclerView()
    }

    //PROJECT EFF

/*    fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewZaku)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = zakuAdapter(zakuProvider.msList)
    }*/

    private fun initRecyclerView() {
        Log.d("tx", "Pasa por la funcion")
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewZaku)
        recyclerView.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            recyclerView.adapter = zakuDbAdapter(db.zakuDao().getAllZakus())
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        // Cerrar la base de datos una vez que ya no se necesite
        db.close()
    }
}