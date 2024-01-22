package com.fz.zakustore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.fz.zakustore.database.entities.zakuEntity
import com.fz.zakustore.database.zakuDatabase
import kotlinx.coroutines.launch

class Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        db = Room.databaseBuilder(this, zakuDatabase::class.java, "zakuStore_db").allowMainThreadQueries().build()
        val intent: Intent = getIntent()
        val bundle = intent.getBundleExtra("bundle")
        val id = bundle?.getInt("zaku")
        val save = findViewById<Button>(R.id.buttonUpSave)
        val model = findViewById<EditText>(R.id.editTextUpModel)
        val nick = findViewById<EditText>(R.id.editTextUpNick)
        val photo = findViewById<EditText>(R.id.editTextUpPhoto)
        val zaku = db.zakuDao()
        var zakuUpdate: zakuEntity

        lifecycleScope.launch {
            if (id != null) {
                zakuUpdate = zaku.getId(id)
                model.setText(zakuUpdate.model).toString()
                nick.setText(zakuUpdate.nick).toString()
                photo.setText(zakuUpdate.photo).toString()

            }
        }

        save.setOnClickListener {
            lifecycleScope.launch {
                if (id != null) {
                    zaku.getId(id).also { zakuUpdate = it }
                    zaku.insert(
                        zakuEntity(
                            zakuUpdate.id,
                            model.text.toString(),
                            nick.text.toString(),
                            photo.text.toString()
                        )
                    )
                }
            }
            Toast.makeText(this, "Actualizado el ${model.text} a la BD", Toast.LENGTH_SHORT).show()
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        // Cerrar la base de datos una vez que ya no se necesite
        db.close()
    }
}