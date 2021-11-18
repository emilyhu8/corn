package com.example.cornspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class EventsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private lateinit var newEvent: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)

        newEvent=findViewById(R.id.newEventButton)

        populateEventList()

    }
    private fun populateEventList() {
        Repository.fetchEventsList(successHandler = {
            runOnUiThread{
                eventAdapter = EventAdapter(it)
                recyclerView.adapter = eventAdapter
            }})
    }

    private fun updateNote(newEventName: String, newEventLocation: String, newEventDetails: String, newEventDate: String) {
        val newEvent = Event(newEventName, newEventLocation, newEventDetails, newEventDate)
        Repository.updateEvent(newEvent)
    }
}