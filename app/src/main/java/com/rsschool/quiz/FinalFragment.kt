package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentFinalBinding
import com.rsschool.quiz.databinding.FragmentQuiz2Binding
import com.rsschool.quiz.databinding.FragmentQuiz4Binding
import com.rsschool.quiz.databinding.FragmentQuizBinding
import kotlin.system.exitProcess

class FinalFragment() : Fragment() {

    private var _binding: FragmentFinalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val count = arguments?.getInt(COUNT_KEY)
        var answers:Array<String> = arguments?.getStringArray(MASS_KEY) as Array<String>

        val answer1 = answers[0]
        val answer2 = answers[1]
        val answer3 = answers[2]
        val answer4 = answers[3]
        val answer5 = answers[4]

        when(count) {
            1 -> binding.textView.text = "Your result is: 1 out of 5"
            2 -> binding.textView.text = "Your result is: 2 out of 5"
            3 -> binding.textView.text = "Your result is: 3 out of 5"
            4 -> binding.textView.text = "Your result is: 4 out of 5"
            5 -> binding.textView.text = "Your result is: 5 out of 5"
            else -> {
                 binding.textView.text = "Your result is: 0 out of 5"
            }
        }
        val result = binding.textView.text.toString()
        val yourResults = """ 
             $result
            
            1)To be or not to be ...
            Your answer is: $answer1
            
            2)The release date of Once upon a time 
            in a Hollywood is...
            Your answer is: $answer2
            
            3)The tallest skyscraper is ...
            Your answer is: $answer3
            
            4)The biggest animal is ...
            Your answer is: $answer4
            
            5)2+2 = 
            Your answer is: $answer5
            
        """

        binding.shareButton.isEnabled = true
        binding.backButton.isEnabled = true
        binding.exitButton.isEnabled = true

        binding.shareButton.setOnClickListener{
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "plain/text"
                putExtra(Intent.EXTRA_EMAIL,"Test@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT,"Your results")
                putExtra(Intent.EXTRA_TEXT, yourResults)
            }
            startActivity(sendIntent)
        }

        binding.backButton.setOnClickListener{
            (activity as MainActivity).openFirstFragment()
        }

        binding.exitButton.setOnClickListener{
            exitProcess(0)
        }
    }



    companion object {
        @JvmStatic
        fun newInstance(count: Int,answers: Array<String>): FinalFragment {
            val fragment = FinalFragment()
            val args = Bundle()

            args.putInt(COUNT_KEY, count)
            args.putStringArray(MASS_KEY, answers)
            fragment.arguments = args
            return fragment
        }

        private const val COUNT_KEY = "COUNT_VALUE"
        private const val  MASS_KEY = "MASS_VALUE"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}