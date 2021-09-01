package com.example.helpdesk.data

data class User(val uid: String? = null,
                val name: String? = null,
                val surname: String? = null,
                val email: String? = null,
                val userRaports: List<String>? = null,
                val image: String? = null,
                val permission:Int? = 0)
