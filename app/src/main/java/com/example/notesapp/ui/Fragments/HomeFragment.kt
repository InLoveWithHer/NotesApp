package com.example.notesapp.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    val notesList: List<Notes> = mutableListOf()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter
        }

        binding.filterHigh.setOnClickListener {

            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }

        }

        binding.allNotes.setOnClickListener {

            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)

            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.filterMedium.setOnClickListener {

            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.filterLow.setOnClickListener {

            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFiltering(newText)
                return true
            }

        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(newText: String?) {

        val newFilteredList = arrayListOf<Notes>()
        for (i in oldMyNotes) {
            if(i.title.contains(newText!!) || i.subTitle.contains(newText!!))
            {
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}