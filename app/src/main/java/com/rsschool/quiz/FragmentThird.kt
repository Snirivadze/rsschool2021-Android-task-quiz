package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuiz2Binding
import com.rsschool.quiz.databinding.FragmentQuiz3Binding
import com.rsschool.quiz.databinding.FragmentQuizBinding

class FragmentThird() : Fragment() {

    private var _binding: FragmentQuiz3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).setTheme(R.style.Theme_Quiz_Third)
        (activity as MainActivity).window.statusBarColor =
            ContextCompat.getColor(activity as MainActivity, R.color.cyan_100_dark)
        _binding = FragmentQuiz3Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.nextButton.isEnabled = false
        binding.previousButton.isEnabled = true
        binding.radioGroup
        binding.question.text = "The biggest animal is..."
        binding.optionOne.text = "blue whale"
        binding.optionTwo.text = "elephant"
        binding.optionThree.text = "cat"
        binding.optionFour.text = "lion"
        binding.optionFive.text = "mouse"

        binding.radioGroup.setOnCheckedChangeListener { _, _, ->
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
                        answers[2] = mass[i].text.toString()
                    (activity as MainActivity).passDateToFourthFragment(count, answers)
                }
            }
        }
        binding.previousButton.setOnClickListener{
            (activity as MainActivity).passDateToSecondFragment(count, answers)
        }

        binding.toolbar.setNavigationOnClickListener{
            (activity as MainActivity).passDateToSecondFragment(count, answers)
        }

    }

    companion object{
        @JvmStatic
        fun newInstance(count:Int, answers: Array<String>): FragmentThird{
            val fragment = FragmentThird()
            val args = Bundle()

            args.putInt(COUNT_KEY, count)
            args.putStringArray(MASS_KEY,answers )
            fragment.arguments = args
            return fragment
        }
        private const val COUNT_KEY = "COUNT_VALUE"
        private const val MASS_KEY = "MASS_VAlUE"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}