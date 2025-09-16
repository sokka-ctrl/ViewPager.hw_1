package com.example.pager.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pager.models.PagerModel
import com.example.pager.databinding.PagerItemBinding

class OnBoardAdapter(
    private val onBoardList: ArrayList<PagerModel>,
    private val onStart: (PagerModel) -> Unit,
    private val onSkip: (PagerModel) -> Unit
) : RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        return OnBoardViewHolder(
            PagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.bind(onBoardList[position])
    }

    override fun getItemCount() = onBoardList.size

    inner class OnBoardViewHolder(private val binding: PagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pagerModel: PagerModel) {

            binding.tvTitle.text = pagerModel.title
            binding.tvDesc.text = pagerModel.desc
            binding.lottieView.setAnimation(pagerModel.lottieImg)
            if (adapterPosition != onBoardList.size - 1) {
                binding.btnStart.visibility = View.INVISIBLE
                binding.tvSkip.visibility = View.VISIBLE
                binding.tvSkip.setOnClickListener {
                    Log.d("onSkip", "clicked ")
                    onSkip(pagerModel)
                }
            } else {
                binding.btnStart.visibility = View.VISIBLE
                binding.tvSkip.visibility = View.GONE
                binding.btnStart.setOnClickListener {
                    binding.btnStart.animate().scaleX(0.90f).scaleY(0.90f).setDuration(1)
                        .withEndAction {
                            itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(1).start()
                        }.start()
                    onStart(pagerModel)
                }
            }
            }
        }
    }