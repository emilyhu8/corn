package com.example.cornspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class EventsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newEvent: Button

    private val names=listOf("Homecoming", "Christmas")
    private val locations=listOf("field", "home")
    private val details=listOf("","")
    private val dates=listOf("Sept 15", "Dec 25")
    private val eventList=mutableListOf<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)



        newEvent=findViewById(R.id.newEventButton)

        //populateEventList()
        for (i in names.indices)
            eventList.add(Event(names[i], locations[i], details[i], dates[i]))

        val eventAdapter=EventAdapter(eventList)
        recyclerView.adapter=eventAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val name = intent.extras?.getString("name")
        val location=intent.extras?.getString("location")
        val details=intent.extras?.getString("details")
        val date=intent.extras?.getString("date")
        val position = intent.extras?.getInt("position")



        newEvent.setOnClickListener{
            eventList.add(Event(" "," "," "," "))
            val intent= Intent(this, EditEventActivity::class.java).apply{
                putExtra("position", eventList.size-1)
            }
            startActivity(intent)
        }

        if (name != null && position != null && details!=null && date!=null) {
            eventList[position] = Event( name, location, details, date)
            eventAdapter.notifyItemChanged(position)

        }
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

    private fun updateNote(newEventName: String, newEventLocation: String, newEventDetails: String, newEventDate: String) {
        val newEvent = Event(newEventName, newEventLocation, newEventDetails, newEventDate)
        Repository.updateEvent(newEvent)
    }
}