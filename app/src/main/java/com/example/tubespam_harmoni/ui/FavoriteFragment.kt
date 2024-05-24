package com.example.tubespam_harmoni

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// FavoriteFragment.kt
class FavoriteFragment : Fragment() {
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvFavoriteMusic)

        val musicDao = MusicDatabase.getDatabase(requireContext()).musicDao()
        val favoriteRepository = FavoriteRepository(musicDao)
        viewModel = ViewModelProvider(this, ViewModelFactory(favoriteRepository)).get(FavoriteViewModel::class.java)

        adapter = MusicAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.favoriteMusic.observe(viewLifecycleOwner, Observer { music ->
            adapter.submitList(music)
        })

        return view
    }
}
