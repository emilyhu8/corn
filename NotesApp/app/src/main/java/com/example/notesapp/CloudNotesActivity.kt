package com.example.notesapp

import android.app.DownloadManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException



const val BASE_URL = "http://143.198.115.54:8080/"

class CloudNotesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val notesList= mutableListOf<Notes>()
    private lateinit var newButton:Button
    private lateinit var localButton:Button

    private val client = OkHttpClient()
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val noteJsonAdapter = moshi.adapter(Notes::class.java)
    private val noteListType = Types.newParameterizedType(List::class.java, Notes::class.java)
    private val noteListJsonAdapter : JsonAdapter<List<Notes>> = moshi.adapter(noteListType)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cloud_notes)

        recyclerView = findViewById(R.id.my_recycler_view)
        recyclerView.setHasFixedSize(true)
        newButton = findViewById(R.id.newButton)
        localButton=findViewById(R.id.localButton)

        val adapter = CloudNotesAdaptor(notesList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val title = intent.extras?.getString("title")
        val body=intent.extras?.getString("body")
        val position = intent.extras?.getInt("position")

        if (title != null && position != null && body!=null ) {
            notesList[position] = Notes( title, body, timestamp=5.0, username = "Bob")
            adapter.notifyItemChanged(position)

        }

        newButton.setOnClickListener{
            val intent=Intent(this, EditNoteActivity::class.java).apply{
                putExtra("title", "")
                putExtra("body", "")
                putExtra("position", position)
            }
            startActivity(intent)
        }

        localButton.setOnClickListener{
            val intent=Intent(this, LocalNotesActivity::class.java)
            startActivity(intent)
        }

    }
    private fun populateNotesList() {
        // val requestGet
    }

    private fun updateNote(newNoteBody: String) {
        val newNote = Notes("foo", -1, "bar")
        val requestPost = DownloadManager.Request.Builder().url(BASE_URL + "posts/")
            .post(noteJsonAdapter.toJson(newNote).toRequestBody(("application/json; charset=utf-8").toMediaType())).build()
        client.newCall(requestPost).execute().use {
            if (!it.isSuccessful) {
                // handle unsuccessful response
            }
            val responseString = it.body!!.string()
        }
    }
}