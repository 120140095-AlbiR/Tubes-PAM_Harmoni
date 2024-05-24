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

// HomeFragment.kt
class HomeFragment : Fragment() {
    private lateinit var viewModel: MusicViewModel
    private lateinit var adapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvPopularMusic)

        val musicRepository = MusicRepository(RetrofitInstance.spotifyService)
        viewModel = ViewModelProvider(this, ViewModelFactory(null, musicRepository)).get(
            MusicViewModel::class.java)

        adapter = MusicAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.musicList.observe(viewLifecycleOwner, Observer { music ->
            adapter.submitList(music)
        })

        viewModel.getPopularMusic()

        return view
    }
}
