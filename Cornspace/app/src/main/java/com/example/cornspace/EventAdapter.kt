package com.example.cornspace

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(private val events: List<Event>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventName: TextView=itemView.findViewById(R.id.eventName)
        val eventLocation: TextView=itemView.findViewById(R.id.eventLocation)
        val eventDetails: TextView=itemView.findViewById(R.id.eventDetails)
        val eventDate: DatePicker=itemView.findViewById(R.id.eventDate)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_event, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) {
        val event = events[position]
        holder.eventName.text=event.name
        holder.eventLocation.text = event.location
        holder.eventDetails.text=event.details


        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
            val noteIntent = Intent(context, EditEventActivity::class.java).apply {
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