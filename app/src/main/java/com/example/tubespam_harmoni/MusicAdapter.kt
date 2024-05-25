package com.example.tubespam_harmoni

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(private val onItemClicked: (Music) -> Unit) : ListAdapter<Music, MusicAdapter.MusicViewHolder>(MusicDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val music = getItem(position)
        holder.bind(music, onItemClicked)
    }

    class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tvMusicTitle)
        private val artistTextView: TextView = itemView.findViewById(R.id.tvMusicArtist)

        fun bind(music: Music, onItemClicked: (Music) -> Unit) {
            titleTextView.text = music.title
            artistTextView.text = music.artist
            itemView.setOnClickListener {
                onItemClicked(music)
            }
        }
    }
}

class MusicDiffCallback : DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem == newItem
    }
}
