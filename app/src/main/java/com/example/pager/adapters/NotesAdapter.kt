package com.example.pager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pager.models.NotesModel
import com.example.pager.databinding.NotesItemBinding

class NotesAdapter(val list_note: ArrayList<NotesModel>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
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
        holder.onBind(list_note[position])
    }

    override fun getItemCount(): Int {
        return list_note.size
    }


    inner class NotesViewHolder(private val binding: NotesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: NotesModel) {
            val colorInt = ContextCompat.getColor(itemView.context, model.notesColor)
            binding.root.background.setTint(colorInt)
            binding.tvNoteTitle.text = model.notesTitle
            binding.tvNotesDesc.text = model.notesDesc
            binding.tvNotesData.text = model.notesData
            itemView.setOnClickListener {
                itemView.animate().scaleX(1.05F).scaleY(1.05F).setDuration(100).withEndAction {
                    itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start()
                }.start()
            }
        }
    }
}