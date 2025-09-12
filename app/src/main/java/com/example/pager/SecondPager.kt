package com.example.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
}
