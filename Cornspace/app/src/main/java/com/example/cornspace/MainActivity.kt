package com.example.cornspace

import android.content.ContentValues.TAG
import android.content.Intent
import android.location.GnssAntennaInfo
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cornspace.notes.NotesActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import org.json.JSONException



import org.json.JSONObject

import org.json.JSONArray
import java.security.AccessController.getContext
import javax.xml.transform.ErrorListener
import javax.xml.transform.TransformerException


class MainActivity : AppCompatActivity() {
    private lateinit var editName: EditText
    private lateinit var hello: TextView
    private lateinit var joke: TextView
    private lateinit var saveName: ImageButton
    private lateinit var linkButton: ImageButton
    private lateinit var eventButton: ImageButton
    private lateinit var noteButton: ImageButton

    private val baseURL = "https://v2.jokeapi.dev"
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editName=findViewById(R.id.editName)
        hello=findViewById(R.id.hello)
        joke=findViewById(R.id.joke)
        saveName=findViewById(R.id.saveName)
        linkButton=findViewById(R.id.linkButton)
        eventButton=findViewById(R.id.eventButton)
        noteButton=findViewById(R.id.noteButton)



        saveName.setOnClickListener{
            Repository.name = editName.text.toString().trim()
            hello.text="Hello, "+ Repository.name+"!"
        }

        linkButton.setOnClickListener{
            val intent= Intent(this, LinksActivity::class.java)
            startActivity(intent)
        }

        eventButton.setOnClickListener{
            val intent=Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }

        noteButton.setOnClickListener{
            val intent= Intent(this, NotesActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onResume() {
        super.onResume()
        hello.text="Hello, "+ Repository.name+"!"
    }

    private fun pickJoke() {

    }


}