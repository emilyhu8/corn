package com.example.cornspace

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editName=findViewById(R.id.editName)
        hello=findViewById(R.id.hello)
        joke=findViewById(R.id.joke)
        saveName=findViewById(R.id.saveName)

        saveName.setOnClickListener{
            val input = editName.text.toString().trim()
            hello.text="Hello, "+ input+"!"
        }




    }
}