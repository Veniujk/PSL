package com.example.helpdesk.home

import androidx.lifecycle.ViewModel
import com.example.helpdesk.data.Raport
import com.example.helpdesk.repository.FirebaseRepository

class HomeViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val raports = repository.getRaports()

    fun adduserRaports(raport: Raport){
        repository.adduserRaports(raport)

    }
}