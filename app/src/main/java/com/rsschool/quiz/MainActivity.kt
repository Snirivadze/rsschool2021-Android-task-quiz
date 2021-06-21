package com.rsschool.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CountPass {

    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFirstFragment()
    }

    fun openFirstFragment() {
        val firstFragment: Fragment = FragmentFirst()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, firstFragment).commit()
    }

    private fun openSecondFragment(count:Int,answers: Array<String>) {
        val secondFragment: Fragment = FragmentSecond.newInstance(count, answers)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, secondFragment).commit()
    }

    private fun openThirdFragment(count:Int,answers: Array<String>) {
        val thirdFragment: Fragment = FragmentThird.newInstance(count,answers)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, thirdFragment).commit()
    }

    private fun openFourthFragment(count:Int, answers: Array<String>) {
        val fourthFragment: Fragment = FragmentFourth.newInstance(count, answers)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fourthFragment).commit()
    }

    private fun openFifthFragment(count:Int, answers: Array<String>) {
        val fifthFragment: Fragment = FragmentFifth.newInstance(count,answers)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fifthFragment).commit()
    }

    private fun openFinalFragment(count:Int,answers: Array<String>) {
        val finalFragment: Fragment = FinalFragment.newInstance(count, answers)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, finalFragment).commit()
    }

    override fun passDateToFirstFragment(){
        openFirstFragment()
    }


    override fun passDateToSecondFragment(count: Int, answers: Array<String>) {
        openSecondFragment(count,answers)
    }

    override fun passDateToThirdFragment(count: Int, answers: Array<String>) {
        openThirdFragment(count,answers)
    }

    override fun passDateToFourthFragment(count: Int, answers: Array<String>) {
        openFourthFragment(count,answers)
    }

    override fun passDateToFifthFragment(count: Int, answers: Array<String>) {
        openFifthFragment(count,answers)
    }


    override fun passDataToFinalFragment(count: Int, answers: Array<String>) {
        openFinalFragment(count,answers)
    }


}