package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button




class MainActivity : AppCompatActivity() {
    private lateinit var cloud: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cloud=findViewById(R.id.cloud)

        cloud.setOnClickListener{
            intent= Intent(this, CloudNotesActivity::class.java)
            startActivity(intent)
        }

    }
}