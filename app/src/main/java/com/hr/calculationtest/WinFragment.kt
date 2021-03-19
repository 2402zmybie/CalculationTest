package com.hr.calculationtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.hr.calculationtest.databinding.FragmentWinBinding

class WinFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentWinBinding>(inflater,R.layout.fragment_win,container, false)
        binding.button11.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_winFragment_to_titleFragment)
        }
        binding.data = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

}