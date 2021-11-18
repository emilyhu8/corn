package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditNoteActivity : AppCompatActivity() {

    private lateinit var titleInput: String
    private lateinit var bodyInput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val position=intent.extras?.getInt("position")
        titleInput=intent.getStringExtra("title").toString()
        bodyInput=intent.getStringExtra("body").toString()

        setContentView(R.layout.activity_edit_note)

        val save: Button = findViewById (R.id.saveButton)
        var title: TextView =findViewById(R.id.noteTitle)
        var body: TextView =findViewById(R.id.noteBody)
        val editTitle: EditText=findViewById(R.id.editTitle)
        val editBody: EditText =findViewById(R.id.editBody)

        title.text=titleInput
        body.text=bodyInput

        save.setOnClickListener{
            val input1 = editTitle?.text.toString().trim()
            val input2= editBody?.text.toString().trim()
            if (input1.isNullOrBlank()==false) {
                titleInput=editTitle.text.toString()
            }
            if (input2.isNullOrBlank()==false) {
                bodyInput=editBody.text.toString()
            }

            val intent= Intent( this, CloudNotesActivity::class.java).apply{
                putExtra("position", position)
                putExtra("title", titleInput)
                putExtra("body", bodyInput)
            }
            startActivity(intent)

        }


    }
}