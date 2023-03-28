package com.example.bmiclaculatermui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Math.round

class mainactivityviewmodels :ViewModel(){


    var totalBMI: MutableLiveData<Double> = MutableLiveData()

    var result:MutableLiveData<String> = MutableLiveData()



    fun calculateBMI( hieght:Double,weight:Double){


        val hieght = hieght.toFloat() / 100
        val BMI = weight.toFloat() / (hieght * hieght)
        val total = (round(BMI * 100) / 100.0)


        totalBMI.value=total.toDouble()



        if (total< 18) {
            result.value= "you are under weight"
        } else if (total >= 18 && total < 25) {
            result.value = "you are healthy"
        } else if (total > 25) {
            result.value= "you are over weight"
        }



    }



}