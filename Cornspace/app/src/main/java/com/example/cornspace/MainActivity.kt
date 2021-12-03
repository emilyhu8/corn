package com.example.cornspace



import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cornspace.notes.NotesActivity


class MainActivity : AppCompatActivity() {
    private lateinit var editName: EditText
    private lateinit var hello: TextView
    private lateinit var addName: TextView
    private lateinit var joke: TextView
    private lateinit var saveName: ImageButton
    private lateinit var linkButton: ImageButton
    private lateinit var eventButton: ImageButton
    private lateinit var noteButton: ImageButton



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
        addName=findViewById(R.id.addName)

        pickJoke()


        saveName.setOnClickListener{
            Repository.name = editName.text.toString().trim()
            hello.text="Hello"
            addName.text = Repository.name+"!"
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
        val rnds = (0..2).random()
        joke.text=Repository.jokes[rnds]
    }

    override fun onResume() {
        super.onResume()
        hello.text="Hello"
        addName.text= Repository.name + "!"
    }

    private fun pickJoke() {

            Repository.fetchJoke(successHandler = {
                runOnUiThread {
                    val rnds = (0..2).random()
                    joke.text=Repository.jokeList[rnds].toString()

                }})


    }


}