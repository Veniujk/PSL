package com.example.psl.home

import androidx.lifecycle.ViewModel
import com.example.psl.data.Raport
import com.example.psl.repository.FirebaseRepository

class HomeViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val raports = repository.getRaports()

    fun adduserRaports(raport: Raport){
        repository.adduserRaports(raport)

    }
}