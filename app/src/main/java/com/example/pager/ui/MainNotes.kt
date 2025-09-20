package com.example.pager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pager.App
import com.example.pager.R
import com.example.pager.adapters.NotesAdapter
import com.example.pager.databinding.FragmentSecondPagerBinding
import com.example.pager.models.NotesModel

class SecondPagerFragment : Fragment() {
    private lateinit var binding: FragmentSecondPagerBinding
    private val notesAdapter = NotesAdapter { note ->
        // удаляем из базы
        App.db.dao().deleteNote(note.id!!)
        // обновляем список
        getData()
    }
    private var boolForNotes = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getData()
        setUpLiester()


        boolForNotes = savedInstanceState?.getBoolean("keyNote") ?: false


        val recyclerView: RecyclerView = binding.rvNotesMain
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.adapter = notesAdapter
        val gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = if (boolForNotes) gridLayoutManager else linearLayoutManager

        binding.ivChangeType.setOnClickListener {

            if (boolForNotes == true) {
                boolForNotes = false
                recyclerView.layoutManager = linearLayoutManager
            } else {
                boolForNotes = true
                recyclerView.layoutManager = gridLayoutManager
            }
        }

        binding.etSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.etSearch.animate().scaleX(1.05F).scaleY(1.05F).setDuration(100).start()
            } else {
                binding.etSearch.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start()
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
            if (!imeVisible) {
                binding.etSearch.clearFocus()
            }
            insets
        }
    }

    private fun initView() {
        binding.rvNotesMain.adapter = notesAdapter
    }

    fun getData() {
        val list: List<NotesModel> = App.Companion.db.dao().getAllNotes()
        notesAdapter.getAllNotes(list)
    }

    fun setUpLiester() {
        binding.btnCreate.setOnClickListener {
            findNavController().navigate(R.id.create_notes)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("keyNote", boolForNotes)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}

