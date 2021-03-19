package com.hr.calculationtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.hr.calculationtest.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java);
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)
        binding.data = viewModel
        binding.lifecycleOwner = requireActivity()
        binding.enter.setOnClickListener {
            val findNavController = Navigation.findNavController(it)
            findNavController.navigate(R.id.action_titleFragment_to_questionFragment)
        }
        return binding.root
    }

}