package com.example.pager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pager.R
import com.example.pager.adapters.OnBoardAdapter
import com.example.pager.databinding.FragmentPagerBinding
import com.example.pager.models.OnBoardModel
import com.example.pref.local.Pref

class PagerFragment : Fragment() {
    private var isOpen: Boolean = true
    private lateinit var pref: Pref
    private lateinit var binding: FragmentPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagerBinding.inflate(inflater, container, false)
        pref = Pref(requireContext())
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val modelList = arrayListOf(
            OnBoardModel(
                "Удобство",
                "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно.",
                R.raw.lottie_one
            ),
            OnBoardModel(
                "Организация",
                "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время.",
                R.raw.lottie_second
            ),
            OnBoardModel(
                "Синхронизация",
                "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте.",
                R.raw.lottie_triple
            )
        )

        val adapter = OnBoardAdapter(modelList, ::onStart, ::onSkip)
        binding.vpViewPager2.adapter = adapter

        val wormDotsIndicator = binding.dotsIndicator
        val viewPager = binding.vpViewPager2
        viewPager.adapter = adapter

        wormDotsIndicator.attachTo(viewPager)
    }

    private fun onSkip(pagerModel: OnBoardModel) {
        binding.vpViewPager2.currentItem = binding.vpViewPager2.size + 1
    }

    private fun onStart(pagerModel: OnBoardModel) {
        pref.saveFirstOpen(true)
        findNavController().navigate(R.id.secondPagerFragment)
    }
}