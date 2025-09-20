package com.example.pager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pager.App
import com.example.pager.R
import com.example.pager.databinding.FragmentCreateNotesBinding
import com.example.pager.models.NotesModel

class Create_notes : Fragment() {
    private var selectedColor: String = "#B4B4B4"

    private lateinit var binding: FragmentCreateNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLister()

    }

    private fun setUpLister() {
        binding.btnYellow.setOnClickListener { selectedColor = "#FFF599" }
        binding.btnRed.setOnClickListener { selectedColor = "#FF9E9E" }
        binding.btnSalad.setOnClickListener { selectedColor = "#91F48F" }
        binding.btnBlue.setOnClickListener { selectedColor = "#9EFFFF" }
        binding.btnSave.setOnClickListener {
            val title: String = binding.etCreateTitle.text.toString()
            val desc: String = binding.etCreateDesc.text.toString()
            val date: String = binding.etCreateDate.text.toString()
            val color = selectedColor
            App.Companion.db.dao().addNote(
                NotesModel(
                    notesTitle = title,
                    notesDesc = desc,
                    notesData = date,
                    notesColor = color
                )
            )
            findNavController().navigate(R.id.secondPagerFragment)
        }
    }

}