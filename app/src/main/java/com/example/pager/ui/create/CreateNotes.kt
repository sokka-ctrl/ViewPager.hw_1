package com.example.pager.ui.create

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
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
import java.text.SimpleDateFormat
import java.util.*

private var note: NotesModel? = null

class CreateNotes : Fragment() {
    private var menuVisible = false
    private var selectedColor: String = "#B4B4B4"

    private lateinit var binding: FragmentCreateNotesBinding
    val args: CreateNotesArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLister()
    }

    @SuppressLint("DefaultLocale")
    private fun setUpLister() {
        binding.viewMenuChange.visibility = View.GONE
        note = args.task
        if (note == null){
            binding.tvDelete.visibility = View.GONE
            binding.ivBine.visibility = View.GONE
            binding.tvStick.visibility = View.GONE
        }
        note?.let {
            binding.etCreateTitle.setText(it.notesTitle)
            binding.etCreateDesc.setText(it.notesDesc)
            binding.tvDate.text = it.notesData
            selectedColor = it.notesColor
        } ?: run {
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            binding.tvDate.text = formatter.format(Date())
        }

        binding.etCreateTitle.addTextChangedListener(object : android.text.TextWatcher{

            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (s.toString().length >= 1 && binding.etCreateDesc.text.length >= 1){
                    binding.btnSave.visibility = View.VISIBLE
                }
                else{
                    binding.btnSave.visibility = View.GONE
                }
            }
        })

        binding.etCreateDesc.addTextChangedListener(object : android.text.TextWatcher{

            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (s.toString().length >= 1  && binding.etCreateTitle.text.length >= 1){
                    binding.btnSave.visibility = View.VISIBLE
                }
                else{
                    binding.btnSave.visibility = View.GONE
                }
            }
        })

        binding.tvDelete.setOnClickListener {
            binding.clDeleteChange.visibility = View.VISIBLE

            binding.btnDeleteChange.setOnClickListener {
                note?.let { safeNote ->
                    App.db.dao().deleteNote(safeNote)
                    findNavController().navigate(R.id.secondPagerFragment)
                }
                binding.clDeleteChange.visibility = View.GONE
            }
            binding.btnDont.setOnClickListener {
                binding.clDeleteChange.visibility = View.GONE
            }
        }

        binding.ivMenu.setOnClickListener {
            menuVisible = !menuVisible
            binding.viewMenuChange.visibility = if (menuVisible) View.VISIBLE else View.GONE
        }


        binding.btnYellow.setOnClickListener { selectColor("#FFF599") }
        binding.btnRed.setOnClickListener { selectColor("#FF9E9E") }
        binding.btnSalad.setOnClickListener { selectColor("#91F48F") }
        binding.btnBlue.setOnClickListener { selectColor("#9EFFFF") }
        binding.btnPink.setOnClickListener { selectColor("#FD99FF") }
        binding.btnPurple.setOnClickListener { selectColor("#B69CFF") }

        binding.btnSave.setOnClickListener {
            appData()
            findNavController().navigate(R.id.action_CreateNotes_to_secondPagerFragment)
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_CreateNotes_to_secondPagerFragment)
        }
    }
    private fun selectColor(color: String) {
        selectedColor = color
        binding.viewMenuChange.visibility = View.GONE
    }

    private fun appData() {
        val title = binding.etCreateTitle.text.toString()
        val desc = binding.etCreateDesc.text.toString()
        val date = binding.tvDate.text.toString()
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
