package com.example.cornspace

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(private val events: MutableList<Event>) : RecyclerView.Adapter<EventAdapter.MyViewHolder>() {

    class MyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventName: TextView=itemView.findViewById(R.id.eventName)
        val eventLocation: TextView=itemView.findViewById(R.id.eventLocation)
        val eventDetails: TextView=itemView.findViewById(R.id.eventDetails)
        val eventDate: TextView=itemView.findViewById(R.id.eventDate)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_event, parent, false) as View
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val event = events[position]
        holder.eventName.text=event.name
        holder.eventLocation.text = event.location
        holder.eventDetails.text=event.details
        holder. eventDate.text=event.date



        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
            val noteIntent = Intent(context, EditEventActivity::class.java).apply {
                putExtra("position", position)
                putExtra("name", event.name)
                putExtra("location", event.location)
                putExtra("details", event.details)
                putExtra("date", event.date)
            }
            context.startActivity(noteIntent)
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }
}