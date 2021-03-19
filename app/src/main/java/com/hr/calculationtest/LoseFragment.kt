package com.hr.calculationtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.hr.calculationtest.databinding.FragmentLoseBinding
import com.hr.calculationtest.databinding.FragmentWinBinding

class LoseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentLoseBinding>(inflater,R.layout.fragment_lose,container, false)
        binding.button10.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loseFragment_to_titleFragment)
        }
        binding.data = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

}