package com.example.spotify

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.songcell.view.*

class SongAdapter (private val songList: MutableList<Songs>)
    : RecyclerView.Adapter<SongAdapter.MyViewHolder>() {

    class MyViewHolder internal constructor (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val songTitle: TextView= itemView.findViewById(R.id.songTitle)
        val songArtist: TextView= itemView.findViewById(R.id.artist)
        val songAlbum: TextView= itemView.findViewById(R.id.album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.songcell, parent, false) as View
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val song =songList[position]
        holder.songTitle.text= song.title
        holder.songArtist.text= song.artist
        holder.songAlbum.text= song.album


        val context=holder.itemView.context
        holder.itemView.setOnClickListener(){
            val intent= Intent(context, SongDetailActivity::class.java).apply {
                putExtra("position", position)
                putExtra("music", song.title)
                putExtra("artist", song.artist)
                putExtra("album", song.album)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return songList.size
    }
}