package com.hr.calculationtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.blankj.utilcode.util.ToastUtils
import com.hr.calculationtest.databinding.FragmentQuestionBinding
import org.jetbrains.anko.db.INTEGER
import java.lang.StringBuilder

class QuestionFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentQuestionBinding;
    lateinit var viewModel:MyViewModel;
    lateinit var builder:StringBuilder;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //viewmodel 和databinding
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        binding = DataBindingUtil.inflate<FragmentQuestionBinding>(
            inflater,
            R.layout.fragment_question,
            container,
            false
        )
        builder = StringBuilder()
        //生成题目
        viewModel.generator()
        binding.data = viewModel
        binding.lifecycleOwner = requireActivity()
        binding.bt0.setOnClickListener(this)
        binding.bt1.setOnClickListener(this)
        binding.bt2.setOnClickListener(this)
        binding.bt3.setOnClickListener(this)
        binding.bt4.setOnClickListener(this)
        binding.bt5.setOnClickListener(this)
        binding.bt6.setOnClickListener(this)
        binding.bt7.setOnClickListener(this)
        binding.bt8.setOnClickListener(this)
        binding.bt9.setOnClickListener(this)
        binding.btc.setOnClickListener(this)
        binding.btok.setOnClickListener{
            //点击提交答案
            if(builder.toString().toInt() == viewModel.getAnswer().value) {
                //答案正确
                viewModel.answerCorrent()
                builder.setLength(0)
                binding.textView9.setText(R.string.answer_corrrect_message)
            }else {
                var findNavController = Navigation.findNavController(it)
                //答案错误
                if(viewModel.win_flag) {
                    //胜利  打破纪录
                    findNavController.navigate(R.id.action_questionFragment_to_winFragment)
                    viewModel.win_flag = false;
                    viewModel.save()
                }else {
                    findNavController.navigate(R.id.action_questionFragment_to_loseFragment)
                }
            }
        }
        return binding.root
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.bt0 -> builder.append("0")
            R.id.bt1 -> builder.append("1")
            R.id.bt2 -> builder.append("2")
            R.id.bt3 -> builder.append("3")
            R.id.bt4 -> builder.append("4")
            R.id.bt5 -> builder.append("5")
            R.id.bt6 -> builder.append("6")
            R.id.bt7 -> builder.append("7")
            R.id.bt8 -> builder.append("8")
            R.id.bt9 -> builder.append("9")
            R.id.btc -> builder.setLength(0)
        }

        if(builder.length == 0) {
            binding.textView9.setText(R.string.input_indicator)
        }else {
            binding.textView9.setText(builder.toString())
        }

    }

}