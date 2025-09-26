package com.example.pager.ui.notes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.pager.data.models.NotesModel
import com.example.pager.databinding.NotesItemBinding

class NotesAdapter(val onClick: (NotesModel) -> Unit, val onLongClick: (NotesModel) -> Unit) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private val listNote = arrayListOf<NotesModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun getAllNotes(list: List<NotesModel>) {
        listNote.clear()
        listNote.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesViewHolder {
        return NotesViewHolder(
            NotesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: NotesViewHolder,
        position: Int
    ) {
        holder.onBind(listNote[position])
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    inner class NotesViewHolder(private val binding: NotesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: NotesModel) {
            binding.back.setBackgroundColor(model.notesColor.toColorInt())
            binding.tvNoteTitle.text = model.notesTitle
            binding.tvNotesDesc.text = model.notesDesc
            binding.tvNotesData.text = model.notesData
            itemView.setOnClickListener {
                itemView.animate().scaleX(1.05f).scaleY(1.05f).setDuration(100).withEndAction {
                    itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start()
                }.start()
                onClick(model)
            }
            itemView.setOnLongClickListener {
                onLongClick(model)
                itemView.animate().scaleX(1.05f).scaleY(1.05f).setDuration(50).withEndAction {
                    itemView.animate().scaleX(0.0f).scaleY(0.0f).setDuration((50))
                }
                true
            }
        }
    }
}