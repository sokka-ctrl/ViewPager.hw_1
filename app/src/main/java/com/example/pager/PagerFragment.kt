package com.example.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.navigation.fragment.findNavController
import com.example.pager.adapters.OnBoardAdapter
import com.example.pager.databinding.FragmentPagerBinding
import com.example.pager.models.PagerModel
import com.example.pref.local.Pref

class PagerFragment : Fragment() {
    private var tepter: Boolean = true
    private lateinit var pref: Pref
    private lateinit var binding: FragmentPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = Pref(binding.root.context)
        if (pref.getTepter() == false) {
            findNavController().navigate(R.id.action_pagerFragment_to_secondPagerFragment)
        }


        val modelList = arrayListOf(
            PagerModel(
                "Удобство",
                "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно.",
                R.raw.lottie_one
            ),
            PagerModel(
                "Организация",
                "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время.",
                R.raw.lottie_second
            ),
            PagerModel(
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

    private fun onSkip(pagerModel: PagerModel) {
        binding.vpViewPager2.currentItem = binding.vpViewPager2.size + 1
    }

    private fun onStart(pagerModel: PagerModel) {

        findNavController().navigate(R.id.action_pagerFragment_to_secondPagerFragment)

        tepter = false
        pref.saveTepter(tepter)
    }
}

//        binding.vpViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                    var marginB =
//                    if (position < 2) 160 else 250
//                marginB = (marginB * resources.displayMetrics.density).toInt()
//                val constraintSet = ConstraintSet()
//                constraintSet.clone(binding.root)
//                constraintSet.setMargin(R.id.dots_indicator, ConstraintSet.BOTTOM, marginB)
//                constraintSet.applyTo(binding.root)
//            }})
