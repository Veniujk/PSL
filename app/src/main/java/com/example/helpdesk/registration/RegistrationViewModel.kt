package com.example.helpdesk.registration

import androidx.lifecycle.ViewModel
import com.example.helpdesk.data.User
import com.example.helpdesk.repository.FirebaseRepository

class RegistrationViewModel: ViewModel() {
    private val repository = FirebaseRepository()

    fun createNewUser(user: User){
        repository.createNewUser(user)
    }

}