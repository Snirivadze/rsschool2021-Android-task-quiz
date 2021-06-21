package com.rsschool.quiz

import android.R
import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuiz2Binding


class FragmentSecond() : Fragment() {

    private var _binding: FragmentQuiz2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).setTheme(com.rsschool.quiz.R.style.Theme_Quiz_Second)
        (activity as MainActivity).window.setStatusBarColor(ContextCompat.getColor(activity as MainActivity, com.rsschool.quiz.R.color.yellow_100_dark))
        _binding = FragmentQuiz2Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.nextButton.isEnabled = false
        binding.previousButton.isEnabled = true
        binding.radioGroup
        binding.question.text = "The release date of Once upon a time in a Hollywood is ..."
        binding.optionOne.text = "24 of July, 2019"
        binding.optionTwo.text = "25 of August, 2019"
        binding.optionThree.text = "24 of August, 2019"
        binding.optionFour.text = "24 of July, 2018"
        binding.optionFive.text = "25 of July, 2019"


        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            binding.nextButton.isEnabled = true
        }

        var count = arguments?.getInt(COUNT_KEY) ?: 0
        var answers:Array<String> = arguments?.getStringArray(MASS_KEY) as Array<String>


        val mass = listOf(binding.optionOne, binding.optionTwo, binding.optionThree, binding.optionFour, binding.optionFive)
        binding.nextButton.setOnClickListener{
            if(binding.optionOne.isChecked){
                count++
            }
            for(i in 0..mass.lastIndex) {
                if(mass[i].isChecked){
                     answers[1] = mass[i].text.toString()
                    (activity as MainActivity).passDateToThirdFragment(count, answers)
                }
            }
        }

        binding.previousButton.setOnClickListener{
            (activity as MainActivity).passDateToFirstFragment()
        }

        binding.toolbar.setNavigationOnClickListener{
            (activity as MainActivity).passDateToFirstFragment()
        }

    }

    companion object{
        @JvmStatic
        fun newInstance(count:Int, answers: Array<String>): FragmentSecond {
            val fragment = FragmentSecond()
            val args = Bundle()

            args.putInt(COUNT_KEY, count)
            args.putStringArray(MASS_KEY,answers)
            fragment.arguments = args
            return fragment
        }
        private const val COUNT_KEY = "COUNT_VALUE"
        private const val MASS_KEY = "MASS_VALUE"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}