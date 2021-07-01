package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding

class FragmentFifth : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).setTheme(R.style.Theme_Quiz_Fifth)
        (activity as MainActivity).window.statusBarColor =
            ContextCompat.getColor(activity as MainActivity, R.color.light_green_100_dark)
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val str = resources.getStringArray(R.array.question_5)

        binding.nextButton.isEnabled = false
        binding.previousButton.isEnabled = true
        binding.nextButton.text = "Submit"

        binding.question.text = str[0]
        binding.optionOne.text = str[1]
        binding.optionTwo.text = str[2]
        binding.optionThree.text = str[3]
        binding.optionFour.text = str[4]
        binding.optionFive.text = str[5]

        binding.toolbar.title = "Question 5"

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            binding.nextButton.isEnabled = true
        }
        var count = arguments?.getInt(COUNT_KEY) ?: 0

        val answers:Array<String> = arguments?.getStringArray(MASS_KEY) as Array<String>

        val mass = listOf(binding.optionOne, binding.optionTwo, binding.optionThree, binding.optionFour, binding.optionFive)
        binding.nextButton.setOnClickListener{
            if(binding.optionOne.isChecked){
                count++
            }
            for(i in 0..mass.lastIndex) {
                if(mass[i].isChecked){
                        answers[4] = mass[i].text.toString()
                    (activity as MainActivity).passDataToFinalFragment(count, answers)
                }
            }
        }
        binding.previousButton.setOnClickListener{
            (activity as MainActivity).passDateToFourthFragment(count, answers)
        }

        binding.toolbar.setNavigationOnClickListener{
            (activity as MainActivity).passDateToFourthFragment(count, answers)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(count: Int,answers:Array<String>): FragmentFifth {
            val fragment = FragmentFifth()
            val args = Bundle()

            args.putInt(COUNT_KEY, count)
            args.putStringArray(MASS_KEY, answers)
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