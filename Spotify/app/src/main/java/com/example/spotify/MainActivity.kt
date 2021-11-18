package com.example.spotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView


    private val songs=listOf("False Direction", "Can I Call You Tonight", "Hot Rod", "Run the World!!!", "Fair Game", "Dear Friend", "Fuzzybrain", "Junior Varisty", "Nicknames", "Listerine" )
    private val artist=listOf("Dayglow","Dayglow", "Dayglow", "Dayglow", "Dayglow", "Dayglow", "Dayglow", "Dayglow", "Dayglow", "Dayglow" )
    private val album=listOf("Fuzzybrain", "Fuzzybrain", "Fuzzybrain", "Fuzzybrain", "Fuzzybrain", "Fuzzybrain", "Fuzzybrain", "Fuzzybrain", "Fuzzybrain", "Fuzzybrain")
    private val songList= mutableListOf<Songs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.my_recycler_view)
        recyclerView.setHasFixedSize(true)

        for (i in songs.indices) {
            songList.add(Songs( songs[i], artist[i], album[i] ))
        }

        val adapter = SongAdapter(songList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val title = intent.extras?.getString("song")
        val artist=intent.extras?.getString("artist")
        val album=intent.extras?.getString("album")
        val position = intent.extras?.getInt("position")

        if (title != null && position != null && artist!=null && album!=null) {
            songList[position] = Songs( title, artist, album)
            adapter.notifyItemChanged(position)

        }

    }

}



