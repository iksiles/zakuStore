package com.fz.zakustore

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.fz.zakustore.database.entities.zakuEntity
import com.fz.zakustore.database.zakuDatabase
import kotlinx.coroutines.launch

class Insert : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val btnSave = findViewById<Button>(R.id.buttonInSave)

        btnSave.setOnClickListener {
            val model = findViewById<EditText>(R.id.editTextModel)
            val nick = findViewById<EditText>(R.id.editTextNick)
            val photo = findViewById<EditText>(R.id.editTextPhoto)
            val intent = Intent(this, MainActivity::class.java)
            db = Room.databaseBuilder(this, zakuDatabase::class.java, "zakuStore_db").build()
            lifecycleScope.launch {
                db.zakuDao().insert(zakuEntity(0,model.text.toString(), nick.text.toString(), photo.text.toString()))
            }
            Toast.makeText(this, "Añadido el ${model.text} a la BD", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        // Cerrar la base de datos una vez que ya no se necesite
        db.close()
    }
}