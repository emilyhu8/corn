package com.example.cornspace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cornspace.notes.NotesActivity

class EventsActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var newEvent: Button


    private var names=listOf("Homecoming Game", "Hanukkah", "Christmas", "February Break")
    private var locations=listOf("Schoellkopf Field", "Home", "Home", "Home")
    private var details=listOf("VMI","National Holiday", "National Holiday", "Short break!")
    private var dates=listOf("Sept 19", "Nov 28-Dec 6","Dec 25", "Feb 26-March 2")



    private var eventAdapter=EventAdapter()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)



        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        newEvent=findViewById(R.id.newEventButton)

        for (i in names.indices)
            Repository.localEventList.add(Event(names[i], locations[i], details[i], dates[i]))


        recyclerView.adapter=eventAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        newEvent.setOnClickListener{

            Repository.localEventList.add(Event("", "", "", ""))

            val intent= Intent(this, EditEventActivity::class.java).apply {
                putExtra("position", Repository.localEventList.size-1)

            }
            startActivity(intent)
        }

        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.divider)!!)
        recyclerView.addItemDecoration(decorator)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        this.intent = intent
    }

    override fun onResume() {
        super.onResume()


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
    }






}