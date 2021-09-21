package com.example.psl.registration

import androidx.lifecycle.ViewModel
import com.example.psl.data.User
import com.example.psl.repository.FirebaseRepository

class RegistrationViewModel: ViewModel() {
    private val repository = FirebaseRepository()

    fun createNewUser(user: User){
        repository.createNewUser(user)
    }

}