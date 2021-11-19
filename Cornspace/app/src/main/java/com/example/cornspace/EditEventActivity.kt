package com.example.cornspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class EditEventActivity : AppCompatActivity() {
    private lateinit var editName:EditText
    private lateinit var editLocation: EditText
    private lateinit var editDetails: EditText
    private lateinit var editDate: EditText
    private lateinit var saveButton: Button

    private lateinit var inputName:String
    private lateinit var inputLocation:String
    private lateinit var inputDetails:String
    private lateinit var inputDate:String

    private lateinit var name: TextView
    private lateinit var location: TextView
    private lateinit var details: TextView
    private lateinit var date: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val position=intent.extras?.getInt("position")
        inputName=intent.getStringExtra("name").toString()
        inputLocation=intent.getStringExtra("location").toString()
        inputDetails=intent.getStringExtra("details").toString()
        inputDate=intent.getStringExtra("date").toString()

        setContentView(R.layout.activity_edit_event)
        editName=findViewById(R.id.editName)
        editLocation=findViewById(R.id.editLocation)
        editDetails=findViewById(R.id.editDetails)
        editDate=findViewById(R.id.editDate)
        saveButton=findViewById(R.id.saveButton)
        name=findViewById(R.id.name)
        location=findViewById(R.id.location)
        details=findViewById(R.id.details)
        date=findViewById(R.id.date)

        name.text=inputName
        location.text=inputLocation
        details.text=inputDetails
        date.text=inputDate

        saveButton.setOnClickListener{
            val input1 = editName?.text.toString().trim()
            val input2 = editLocation?.text.toString().trim()
            val input3 = editDetails?.text.toString().trim()
            val input4 = editDate?.text.toString().trim()
            if (input1.isNullOrBlank()==false) {
                inputName=input1
            }
            if (input2.isNullOrBlank()==false) {
                 inputLocation=input2
            }
            if (input3.isNullOrBlank()==false) {
                 inputDetails=input3
            }
            if (input4.isNullOrBlank()==false) {
                inputDate=input4
            }

            val intent= Intent(this, EventsActivity::class.java).apply{
                putExtra("position", position)
                putExtra("name", inputName)
                putExtra("location", inputLocation)
                putExtra("details", inputDetails)
                putExtra("date", inputDate)
            }

            startActivity(intent)

        }


    }
}