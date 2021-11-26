package com.example.cornspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import java.util.logging.Logger

class EventsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newEvent: Button
    private lateinit var linkButton: Button
    private lateinit var homeButton: Button
    private lateinit var noteButton: Button

    private var names=listOf("Homecoming", "Christmas")
    private var locations=listOf("field", "home")
    private var details=listOf("","")
    private var dates=listOf("Sept 15", "Dec 25")

    private var eventList=mutableListOf<Event>()

    private var eventAdapter=EventAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Emily", "onCreate")
        setContentView(R.layout.activity_events)

        linkButton=findViewById(R.id.linkButton)
        homeButton=findViewById(R.id.homeButton)
        noteButton=findViewById(R.id.noteButton)

        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)



        newEvent=findViewById(R.id.newEventButton)

        for (i in names.indices)
            Repository.localEventList.add(Event(names[i], locations[i], details[i], dates[i]))

        linkButton.setOnClickListener{
            val intent= Intent(this, LinksActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener{
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        noteButton.setOnClickListener{

        }

        recyclerView.adapter=eventAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        newEvent.setOnClickListener{

            Repository.localEventList.add(Event("", "", "", ""))

            val intent= Intent(this, EditEventActivity::class.java).apply {
                putExtra("position", Repository.localEventList.size-1)

            }
            startActivity(intent)
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("Emily","onNewIntent")
        this.intent = intent
    }

    override fun onResume() {
        super.onResume()
        Log.d("Emily", "onResume")

        //populateEventList()

        val name = intent.extras?.getString("name")
        val location=intent.extras?.getString("location")
        val details=intent.extras?.getString("details")
        val date=intent.extras?.getString("date")
        val position = intent.extras?.getInt("position")

        if (name != null && position != null && details!=null && date!=null) {
            Repository.localEventList[position] = Event( name, location, details, date)
            eventAdapter = EventAdapter()
            recyclerView.adapter=eventAdapter

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Emily", "onDestroy")
    }
    private fun populateEventList() {

        /*
        Repository.fetchEventsList(successHandler = {
            runOnUiThread{
                eventAdapter = EventAdapter(it)
                recyclerView.adapter = eventAdapter
            }})

         */

    }
/*
    private fun updateNote(newEventName: String, newEventLocation: String, newEventDetails: String, newEventDate: String) {
        val newEvent = Event(newEventName, newEventLocation, newEventDetails, newEventDate)
        Repository.updateEvent(newEvent)
    }

 */
}