package com.example.cornspace



import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cornspace.notes.NotesActivity


class MainActivity : BaseActivity() {
    private lateinit var editName: EditText
    private lateinit var hello: TextView
    private lateinit var addName: TextView
    private lateinit var joke: TextView
    private lateinit var saveName: ImageButton




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editName=findViewById(R.id.editName)
        hello=findViewById(R.id.hello)
        joke=findViewById(R.id.joke)
        saveName=findViewById(R.id.saveName)
        addName=findViewById(R.id.addName)

        pickJoke()



        saveName.setOnClickListener{
            Repository.name = editName.text.toString().trim()
            hello.text="Hello"
            addName.text = Repository.name+"!"
        }



    }

    override fun onResume() {
        super.onResume()
        hello.text=getString(R.string.Hello)
        addName.text= Repository.name + "!"
    }

    private fun pickJoke() {

            Repository.fetchJoke(successHandler = {
                runOnUiThread {

                    joke.text=Repository.joke.data

                }})


    }


}