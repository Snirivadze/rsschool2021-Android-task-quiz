package com.rsschool.quiz


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding

class FragmentFirst : Fragment() {

    private  var _binding: FragmentQuizBinding? = null
    private  val binding get() = _binding!!
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).setTheme(R.style.Theme_Quiz_First)
        (activity as MainActivity).window.statusBarColor =
            ContextCompat.getColor(activity as MainActivity, R.color.deep_orange_100_dark)
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val str: Array<String> = resources.getStringArray(R.array.question_1)

        binding.nextButton.isEnabled = false
        binding.previousButton.isEnabled = false

        binding.question.text = str[0]
        binding.optionOne.text = str[1]
        binding.optionTwo.text = str[2]
        binding.optionThree.text = str[3]
        binding.optionFour.text = str[4]
        binding.optionFive.text = str[5]

        binding.toolbar.navigationIcon = null
        binding.toolbar.title = "Question 1"

       binding.radioGroup.setOnCheckedChangeListener{_, _ ->
            binding.nextButton.isEnabled = true
       }
        val mass = listOf(binding.optionOne, binding.optionTwo, binding.optionThree, binding.optionFour, binding.optionFive)
        val answers: Array<String> = arrayOf("","","","","","")
       binding.nextButton.setOnClickListener{
           if(binding.optionFive.isChecked){
                count++
           }
           for(i in 0..mass.lastIndex) {
               if(mass[i].isChecked){
                       answers[0] = mass[i].text.toString()
                   (activity as MainActivity).passDateToSecondFragment(count, answers)
               }
           }
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}