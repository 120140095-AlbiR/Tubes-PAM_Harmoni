package com.example.tubespam_harmoni

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MusicDetailFragment : Fragment() {

    private lateinit var viewModel: MusicDetailViewModel
    private lateinit var music: Music
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music_detail, container, false)

        // Initialize UI components
        val ivMusicCover: ImageView = view.findViewById(R.id.ivMusicCover)
        val tvMusicTitle: TextView = view.findViewById(R.id.tvMusicTitle)
        val tvMusicArtist: TextView = view.findViewById(R.id.tvMusicArtist)
        val btnAddToFavorites: Button = view.findViewById(R.id.btnAddToFavorites)
        val btnPlayPause: ImageButton = view.findViewById(R.id.btnPlayPause)
        val btnPrevious: ImageButton = view.findViewById(R.id.btnPrevious)
        val btnNext: ImageButton = view.findViewById(R.id.btnNext)

        // Get passed music data
        arguments?.let {
            music = MusicDetailFragmentArgs.fromBundle(it).music
        }

        // Set music data to UI
        tvMusicTitle.text = music.title
        tvMusicArtist.text = music.artist
        // Set image resource (You can use Glide or Picasso for loading images)
        // ivMusicCover.setImageResource(music.coverResId) // Assuming there's a resource id

        // Initialize ViewModel
        val musicDao = MusicDatabase.getDatabase(requireContext()).musicDao()
        val favoriteRepository = FavoriteRepository(musicDao)
        viewModel = ViewModelProvider(this, FavoriteViewModelFactory(favoriteRepository)).get(MusicDetailViewModel::class.java)

        // Check if the music is already a favorite
        viewModel.isFavorite(music.id).observe(viewLifecycleOwner, Observer { isFav ->
            isFavorite = isFav
            btnAddToFavorites.text = if (isFavorite) "Remove from Favorites" else "Add to Favorites"
        })

        // Add or remove music from favorites on button click
        btnAddToFavorites.setOnClickListener {
            if (isFavorite) {
                viewModel.removeFavorite(music)
            } else {
                viewModel.addFavorite(music)
            }
        }

        // Handle playback controls
        btnPlayPause.setOnClickListener {
            // Handle play/pause logic here
        }

        btnPrevious.setOnClickListener {
            // Handle previous logic here
        }

        btnNext.setOnClickListener {
            // Handle next logic here
        }

        return view
    }
}
