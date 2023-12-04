package com.exercise.finalfourexam.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.finalfourexam.adapter.HomeAdapter
import com.exercise.finalfourexam.databinding.FragmentHomeBinding
import com.exercise.finalfourexam.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var homeAdapter: HomeAdapter

    override fun setUp() {
        prepareRecyclerView()
        
    }

    private fun prepareRecyclerView() {
        homeAdapter = HomeAdapter()
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }
    }

}