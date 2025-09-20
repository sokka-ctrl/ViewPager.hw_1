package com.example.pager.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pager.models.NotesModel
import com.example.pager.databinding.NotesItemBinding

class NotesAdapter(private val onItemLongClick: (NotesModel) -> Unit ) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private val listNote = arrayListOf<NotesModel>()

    fun addNotes(list: List<NotesModel>) {
        listNote.clear()
        listNote.addAll(list)
    }

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
            binding.back.setBackgroundColor(Color.parseColor(model.notesColor))
            binding.tvNoteTitle.text = model.notesTitle
            binding.tvNotesDesc.text = model.notesDesc
            binding.tvNotesData.text = model.notesData
            itemView.setOnClickListener {
                itemView.animate().scaleX(1.05f).scaleY(1.05f).setDuration(100).withEndAction {
                    itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start()
                }.start()
            }
            itemView.setOnLongClickListener {
                itemView.animate().scaleX(1.05f).scaleY(1.05f).setDuration(50).withEndAction {
                    itemView.animate().scaleX(0.0f).scaleY(0.0f).setDuration((50))
                }
                onItemLongClick(model)
                true
            }
        }
    }
}