package com.example.cornspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cornspace.notes.NotesActivity

class LinksActivity : AppCompatActivity() {
    private lateinit var link1: TextView
    private lateinit var link2: TextView
    private lateinit var link3: TextView
    private lateinit var link4: TextView
    private lateinit var link5: TextView
    private lateinit var link6: TextView
    private lateinit var link7: TextView
    private lateinit var link8: TextView
    private lateinit var eventButton: Button
    private lateinit var homeButton: Button
    private lateinit var noteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_links)
        link1=findViewById(R.id.link1)
        link2=findViewById(R.id.link2)
        link3=findViewById(R.id.link3)
        link4=findViewById(R.id.link4)
        link5=findViewById(R.id.link5)
        link6=findViewById(R.id.link6)
        link7=findViewById(R.id.link7)
        link8=findViewById(R.id.link8)

        eventButton=findViewById(R.id.eventButton)
        homeButton=findViewById(R.id.homeButton)
        noteButton=findViewById(R.id.noteButton)

        homeButton.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        eventButton.setOnClickListener{
            val intent= Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }

        noteButton.setOnClickListener{
            val intent= Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

    }
}