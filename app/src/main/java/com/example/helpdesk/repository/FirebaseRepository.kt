package com.example.helpdesk.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.example.helpdesk.data.Raport
import com.example.helpdesk.data.User


class FirebaseRepository {
    private val REPO_DEBUG = "REPO_DEBUG"

    private val storage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val cloud = FirebaseFirestore.getInstance()

    fun uploadUserPhoto(bytes: ByteArray){
        storage.getReference("users")
                .child("${auth.currentUser!!.uid}.jpg")
                .putBytes(bytes)
                .addOnCompleteListener{
                    Log.d(REPO_DEBUG, "COMPLETE UPLOAD PHOTO")
                }
                .addOnSuccessListener {
                    getPhotoDownloadUrl(it.storage)
                }
                .addOnFailureListener{
                    Log.d(REPO_DEBUG, it.message.toString())
                }
    }

    private fun getPhotoDownloadUrl(storage: StorageReference) {
        storage.downloadUrl
                .addOnSuccessListener {
                    updateUserPhoto(it.toString())
                }
                .addOnFailureListener{
                    Log.d(REPO_DEBUG, it.message.toString())
                }
    }

    private fun updateUserPhoto(url: String?) {
        cloud.collection("users")
                .document(auth.currentUser!!.uid)
                .update("image", url)
                .addOnSuccessListener {
                    Log.d(REPO_DEBUG, "UPDATE USER PHOTO!")
                }
                .addOnFailureListener{
                    Log.d(REPO_DEBUG, it.message.toString())
                }
    }

    fun getUserData(): LiveData<User>{
        val cloudResult = MutableLiveData<User>()
        val uid = auth.currentUser?.uid

        cloud.collection("users")
            .document(uid!!)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(User::class.java)
                cloudResult.postValue(user)
            }
            .addOnFailureListener{
                Log.d(REPO_DEBUG, it.message.toString())
            }

        return cloudResult
    }

    fun getRaports(): LiveData<List<Raport>>{
        val cloudResult = MutableLiveData<List<Raport>>()

        cloud.collection("raports")
            .get()
            .addOnSuccessListener {
                val user = it.toObjects(Raport::class.java)
                cloudResult.postValue(user)
            }
            .addOnFailureListener{
                Log.d(REPO_DEBUG, it.message.toString())
            }
        return cloudResult
    }
    fun getFavCars(list: List<String>?): LiveData<List<Raport>> {
        val cloudResult = MutableLiveData<List<Raport>>()
            cloud.collection("raports")
                .whereEqualTo("uid",auth.currentUser?.uid!! )
               .get()
               .addOnSuccessListener {
                   val resultList = it.toObjects(Raport::class.java)
                   cloudResult.postValue(resultList)
               }
               .addOnFailureListener{
                   Log.d(REPO_DEBUG, it.message.toString())
               }
        return cloudResult
    }
    fun adduserRaports(raport : Raport){
        cloud.collection("users")
            .document(auth.currentUser?.uid!!)
            .update("userRaports", FieldValue.arrayUnion(raport.id))
            .addOnSuccessListener {
                Log.d(REPO_DEBUG, "Dodana do ulubionych")
            }
            .addOnFailureListener{
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }
    fun removeuserRaports(raport : Raport){
        cloud.collection("users")
            .document(auth.currentUser?.uid!!)
            .update("userRaports", FieldValue.arrayRemove(raport.id))
            .addOnSuccessListener {
                Log.d(REPO_DEBUG, "Usunieta z ulubionych")
            }
            .addOnFailureListener{
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }

    fun editRaports(raport : Raport){
        cloud.collection("raports")
            .document()
            .update("id", FieldValue.arrayRemove(raport.id))
            .addOnSuccessListener {
                Log.d(REPO_DEBUG, "Usunieta z ulubionych")
            }
            .addOnFailureListener{
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }
    fun createNewUser(user: User){
        cloud.collection("users")
                .document(user.uid!!)
                .set(user)
    }
    fun editProfileData(map: Map<String, String>){
        cloud.collection("users")
                .document(auth.currentUser!!.uid)
                .update(map)
                .addOnSuccessListener {
                    Log.d(REPO_DEBUG, "Zaktualizowano dane!")
                }
                .addOnFailureListener{
                    Log.d(REPO_DEBUG, it.message.toString())
                }
    }



}