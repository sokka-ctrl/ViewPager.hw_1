package com.example.pager.ui.notes.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.pager.data.models.NotesModel
import com.example.pager.databinding.NotesItemBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter(
    private val onClick: (NotesModel) -> Unit,
    private val onLongClick: (NotesModel) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val listNote = arrayListOf<NotesModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun getAllNotes(list: List<NotesModel>) {
        listNote.clear()
        listNote.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            NotesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.onBind(listNote[position])
    }

    override fun getItemCount(): Int = listNote.size

    inner class NotesViewHolder(private val binding: NotesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun onBind(model: NotesModel) {
            val drawable = binding.root.background as? GradientDrawable

            try {
                drawable?.setColor(model.notesColor.toColorInt())
            } catch (e: IllegalArgumentException) {
                val colorInt = ContextCompat.getColor(binding.root.context, model.notesColor.toInt())
                drawable?.setColor(colorInt)
            }

            binding.tvNoteTitle.text = model.notesTitle
            binding.tvNotesDesc.text = model.notesDesc
            binding.tvNotesData.text = model.notesData.convertDate()

            itemView.setOnClickListener { onClick(model) }
            itemView.setOnLongClickListener {
                onLongClick(model)
                true
            }
        }

        private fun String.convertDate(): String {
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("HH:mm dd.MM", Locale.getDefault())
                val date = inputFormat.parse(this)
                date?.let { outputFormat.format(it) } ?: this
            } catch (e: Exception) {
                this
            }
        }
    }

    fun removeNote(note: NotesModel) {
        val index = listNote.indexOf(note)
        if (index != -1) {
            listNote.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}
