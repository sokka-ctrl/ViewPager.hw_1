package com.example.pager.ui.create

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pager.App
import com.example.pager.R
import com.example.pager.data.models.NotesModel
import com.example.pager.databinding.FragmentCreateNotesBinding
import java.util.Calendar

private var note: NotesModel? = null

class CreateNotes : Fragment() {
    private var menuVisible = false
    private var selectedColor: String = "#B4B4B4"
    val args: CreateNotesArgs by navArgs()

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

    @SuppressLint("DefaultLocale")
    private fun setUpLister() {
        binding.tvDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val datePicker = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    val timePicker = TimePickerDialog(
                        requireContext(),
                        { _, selectedHour, selectedMinute ->
                            val selectedDateTime =
                                String.format(
                                    "%02d.%02d.%04d %02d:%02d",
                                    selectedDay,
                                    selectedMonth + 1,
                                    selectedYear,
                                    selectedHour,
                                    selectedMinute
                                )
                            binding.tvDate.text = selectedDateTime
                        },
                        hour,
                        minute,
                        true
                    )
                    timePicker.show()
                },
                year,
                month,
                day
            )
            datePicker.show()

        }


        binding.viewMenuChange.visibility = View.GONE
        note = args.task
        note?.let {
            binding.etCreateTitle.setText(it.notesTitle)
            binding.etCreateDesc.setText(it.notesDesc)
            binding.tvDate.setText(it.notesData)
            selectedColor = it.notesColor
        }

        binding.tvDelete.setOnClickListener {
            note?.let { safeNote ->
                App.db.dao().deleteNote(safeNote)
                findNavController().navigate(R.id.secondPagerFragment)
            }
        }


        binding.ivMenu.setOnClickListener {
            if (menuVisible == true) {
                menuVisible = false
            } else {
                menuVisible = true
            }
            if (menuVisible == false) {
                binding.viewMenuChange.visibility = View.GONE
            } else {
                binding.viewMenuChange.visibility = View.VISIBLE
            }
        }
        
        binding.btnYellow.setOnClickListener {
            selectedColor = "#FFF599"
            binding.viewMenuChange.visibility = View.GONE
        }
        binding.btnRed.setOnClickListener {
            selectedColor = "#FF9E9E"
            binding.viewMenuChange.visibility = View.GONE
        }
        binding.btnSalad.setOnClickListener {
            selectedColor = "#91F48F"
            binding.viewMenuChange.visibility = View.GONE
        }
        binding.btnBlue.setOnClickListener {
            selectedColor = "#9EFFFF"
            binding.viewMenuChange.visibility = View.GONE
        }
        binding.btnPink.setOnClickListener {
            selectedColor = "#FD99FF"
            binding.viewMenuChange.visibility = View.GONE
        }
        binding.btnPurple.setOnClickListener {
            selectedColor = "#B69CFF"
            binding.viewMenuChange.visibility = View.GONE
        }
        binding.btnSave.setOnClickListener {
            appData()
            findNavController().navigate(R.id.secondPagerFragment)
        }
    }

    private fun appData() {
        note = args.task
        val title: String = binding.etCreateTitle.text.toString()
        val desc: String = binding.etCreateDesc.text.toString()
        val date: String = binding.tvDate.text.toString()
            val color = selectedColor
        if (note != null) {
            App.db.dao().updateNote(
                note!!.copy(
                    notesTitle = title,
                    notesDesc = desc,
                    notesData = date,
                    notesColor = color
                )
            )
        } else {
            val newNote = NotesModel(
                    notesTitle = title,
                    notesDesc = desc,
                    notesData = date,
                    notesColor = color
                )
            App.db.dao().addNote(newNote)
        }
        }

    }


