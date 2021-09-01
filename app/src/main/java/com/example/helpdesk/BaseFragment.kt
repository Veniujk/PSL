package com.example.helpdesk

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import com.example.helpdesk.activites.MainActivity
import kotlin.math.roundToInt

abstract class BaseFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val transInflater = TransitionInflater.from(requireContext())
        enterTransition = transInflater.inflateTransition(R.transition.slide_right)
        exitTransition = transInflater.inflateTransition(R.transition.fade_out)
    }
    protected fun startApp(){
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }

    fun control(data:HashMap<Any,Any>): Int {
        var suma = 0
        for ((key,value) in data) {

            if (value == true) {
                suma += 1
            }
        }

        return ((suma.toDouble() / data.size) * 100).roundToInt()
    }
}