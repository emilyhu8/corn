package com.example.spotify


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.spotifyactivity.*

class SongDetailActivity: AppCompatActivity() {

    private lateinit var titleInput: String
    private lateinit var artistInput: String
    private lateinit var albumInput: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val position=intent.extras?.getInt("position")
        titleInput=intent.getStringExtra("music").toString()
        artistInput=intent.getStringExtra("artist").toString()
        albumInput=intent.getStringExtra("album").toString()

        setContentView(R.layout.spotifyactivity)

        val save: Button = findViewById(R.id.saveButton)
        var name: TextView=findViewById(R.id.songTitle)
        var artist:TextView=findViewById(R.id.artist)
        var album: TextView=findViewById(R.id.album)

        name.text=titleInput
        artist.text=artistInput
        album.text=albumInput

        save.setOnClickListener{
            val input1 = editTitle?.text.toString().trim()
            val input2 = editArtist?.text.toString().trim()
            val input3 = editAlbum?.text.toString().trim()
            if (input1.isNullOrBlank()==false) {
                titleInput=editTitle.text.toString()
            }
            if (input2.isNullOrBlank()==false) {
                artistInput=editArtist.text.toString()
            }
            if (input3.isNullOrBlank()==false) {
                albumInput=editAlbum.text.toString()
            }

            val intent= Intent(this, MainActivity::class.java).apply{
                putExtra("position", position)
                putExtra("song", titleInput)
                putExtra("artist", artistInput)
                putExtra("album", albumInput)
            }

            startActivity(intent)
       }

        val sharebutton: Button= findViewById(R.id.sharebutton)
        sharebutton.setOnClickListener(){
            val message = "Check out this song: " + titleInput
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "subject here")
            startActivity(Intent.createChooser(shareIntent, "Share text via"))


        }
    }
}