package com.rsschool.quiz

interface CountPass {
    fun passDateToFirstFragment()
    fun passDateToSecondFragment(count: Int, answer: Array<String>)
    fun passDateToThirdFragment(count:Int, answer: Array<String>)
    fun passDateToFourthFragment(count:Int, answer: Array<String>)
    fun passDateToFifthFragment(count:Int, answer: Array<String>)
    fun passDataToFinalFragment(count: Int, answer: Array<String>)
}