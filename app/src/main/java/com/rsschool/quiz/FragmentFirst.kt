package com.rsschool.quiz

import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding

class FragmentFirst() : Fragment() {

    private  var _binding: FragmentQuizBinding? = null
    private  val binding get() = _binding!!
    private var count = 0



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).setTheme(R.style.Theme_Quiz_First)
        (activity as MainActivity).window.setStatusBarColor(ContextCompat.getColor(activity as MainActivity, R.color.deep_orange_100_dark))
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.nextButton.isEnabled = false
        binding.previousButton.isEnabled = false
        binding.radioGroup
        binding.question.text = "To be or not to be..."
        binding.optionOne.text = "To Be"
        binding.optionTwo.text = "Not To Be"
        binding.optionThree.text = "I don't know"
        binding.optionFour.text = "it's not my problem"
        binding.optionFive.text = "... That is the question!"

      binding.toolbar.navigationIcon = null


       binding.radioGroup.setOnCheckedChangeListener{_, _, ->
            binding.nextButton.isEnabled = true
       }
        val mass = listOf(binding.optionOne, binding.optionTwo, binding.optionThree, binding.optionFour, binding.optionFive)
        var answers: Array<String> = arrayOf("","","","","","")
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