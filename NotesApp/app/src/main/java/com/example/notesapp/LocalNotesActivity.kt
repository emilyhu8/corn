package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class LocalNotesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cloudButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_notes)

        recyclerView = findViewById(R.id.my_recycler_view)
        recyclerView.setHasFixedSize(true)

        cloudButton=findViewById(R.id.cloudButton)


        cloudButton.setOnClickListener{
            val intent= Intent(this, CloudNotesActivity::class.java)
            startActivity(intent)
        }
    }
}