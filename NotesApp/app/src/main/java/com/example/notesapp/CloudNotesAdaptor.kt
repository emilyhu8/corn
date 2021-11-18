package com.example.notesapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CloudNotesAdaptor (private val notesList: MutableList<Notes>)
: RecyclerView.Adapter<CloudNotesAdaptor.MyViewHolder>()  {

    class MyViewHolder internal constructor (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitle: TextView = itemView.findViewById(R.id.noteTitle)
        val noteBody: TextView = itemView.findViewById(R.id.noteBody)
        val timestamp: TextView = itemView.findViewById(R.id.timestamp)
        val username: TextView = itemView.findViewById(R.id.username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_edit_note, parent, false) as View
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note= notesList[position]
        holder.noteTitle.text=note.title
        holder.noteBody.text=note.body.take(150)
        holder.timestamp.text=note.timestamp.toString()
        holder.username.text=note.username


        val context=holder.itemView.context
        holder.itemView.setOnClickListener(){
            val intent= Intent(context, EditNoteActivity::class.java).apply {
                putExtra("position", position)
                putExtra("title", note.title)
                putExtra("body", note.body)
                putExtra("timestamp", note.timestamp)
                putExtra("username", note.username)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}