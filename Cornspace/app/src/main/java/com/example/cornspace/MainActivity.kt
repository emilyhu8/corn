package com.example.cornspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var editName: EditText
    private lateinit var hello: TextView
    private lateinit var joke: TextView
    private lateinit var saveName: Button
    private lateinit var linkButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editName=findViewById(R.id.editName)
        hello=findViewById(R.id.hello)
        joke=findViewById(R.id.joke)
        saveName=findViewById(R.id.saveName)
        linkButton=findViewById(R.id.linkButton)

        saveName.setOnClickListener{
            val input = editName.text.toString().trim()
            hello.text="Hello, "+ input+"!"
        }

        linkButton.setOnClickListener{
            val intent= Intent(this, LinksActivity::class.java)
            startActivity(intent)
        }





    }
}