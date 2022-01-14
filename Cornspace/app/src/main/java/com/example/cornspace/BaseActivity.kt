package com.example.cornspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.cornspace.notes.NotesActivity

open class BaseActivity : AppCompatActivity() {
    private lateinit var linkButton: ImageButton
    private lateinit var eventButton: ImageButton
    private lateinit var noteButton: ImageButton
    private lateinit var homeButton: ImageButton

    override fun onResume() {
        super.onResume()
        linkButton=findViewById(R.id.linkButton)
        eventButton=findViewById(R.id.eventButton)
        noteButton=findViewById(R.id.noteButton)
        homeButton = findViewById(R.id.homeButton)

        linkButton.setOnClickListener{
            val intent= Intent(this, LinksActivity::class.java)
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

        homeButton.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragments = supportFragmentManager.fragments
        if (fragments.size == 0){
            finish()
        }
    }
}