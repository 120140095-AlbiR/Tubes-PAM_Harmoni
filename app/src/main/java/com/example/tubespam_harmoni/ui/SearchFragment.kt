package com.example.tubespam_harmoni

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tubespam_harmoni.R

// SearchFragment.kt
class SearchFragment : Fragment() {
    private lateinit var viewModel: MusicViewModel
    private lateinit var adapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val editText = view.findViewById<EditText>(R.id.etSearch)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvSearchResults)

        val musicRepository = MusicRepository(RetrofitInstance.spotifyService)
        viewModel = ViewModelProvider(this, ViewModelFactory(null, musicRepository)).get(MusicViewModel::class.java)

        adapter = MusicAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.musicList.observe(viewLifecycleOwner, Observer { music ->
            adapter.submitList(music)
        })

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                s?.toString()?.let { query ->
                    if (query.isNotEmpty()) {
                        viewModel.searchMusic(query)
                    }
                }
            }
        })

        return view
    }
}
