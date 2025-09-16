package com.example.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pager.databinding.FragmentSecondPagerBinding

class SecondPagerFragment : Fragment() {
    private lateinit var binding: FragmentSecondPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val notesList: ArrayList<NotesModel> = ArrayList<NotesModel>()
        val idem1 = NotesModel(
            "План на жизнь",
            "Посадить сына, вырастить дом, построить дерево. Нужно",
            "31 мая 12:45",
            R.color.light_red
        )
        val idem2 = NotesModel(
            "Нужно сделать",
            "Работы с проектом, сделать домашку, построить бизнес и",
            "31 мая 12:45",
            R.color.banana
        )
        val idem3 = NotesModel(
            "План на жизнь:",
            "Работы с проектом, сделать домашку, построить бизнес и",
            "31 мая 12:45",
            R.color.salad
        )
        val idem4 = NotesModel(
            "План на жизнь",
            "Посадить сына, вырастить дом, построить дерево. Нужно",
            "31 мая 12:45",
            R.color.light_red
        )
        val idem5 = NotesModel(
            "Нужно сделать",
            "Работы с проектом, сделать домашку, построить бизнес и",
            "31 мая 12:45",
            R.color.banana
        )
        val idem6 = NotesModel(
            "План на жизнь:",
            "Работы с проектом, сделать домашку, построить бизнес и",
            "31 мая 12:45",
            R.color.salad
        )
        val idem7 = NotesModel(
            "План на жизнь",
            "Посадить сына, вырастить дом, построить дерево. Нужно",
            "31 мая 12:45",
            R.color.light_red
        )
        val idem8 = NotesModel(
            "Нужно сделать",
            "Работы с проектом, сделать домашку, построить бизнес и",
            "31 мая 12:45",
            R.color.banana
        )
        val idem9 = NotesModel(
            "План на жизнь:",
            "Работы с проектом, сделать домашку, построить бизнес и",
            "31 мая 12:45",
            R.color.salad
        )
        notesList.add(idem1)
        notesList.add(idem2)
        notesList.add(idem3)
        notesList.add(idem4)
        notesList.add(idem5)
        notesList.add(idem6)
        notesList.add(idem7)
        notesList.add(idem8)
        notesList.add(idem9)


        val notesAdapter = NotesAdapter(notesList)
        val recyclerView: RecyclerView = binding.rvNotesMain
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.adapter = notesAdapter
        recyclerView.layoutManager = linearLayoutManager

        binding.etSearch.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {
                    binding.etSearch.animate().scaleX(1.10F).scaleY(1.10F).setDuration(100)
                } else {
                    binding.etSearch.animate().scaleX(1.05F).scaleY(1.05F).setDuration(100)
                        .withEndAction {
                            binding.etSearch.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100)
                                .start()
                        }.start()

                }
            }
        })
    }

}
