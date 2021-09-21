package com.example.psl.data

import android.os.Parcelable
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Raport (val id: String? = null,
                   val name: String? = null,
                   val subject: String? = null,
                   val message:String? = null,
                   val date: Timestamp? = null,
                   val control: String? = null,
                   val deadline: String? = null,
                   val uid:String? = null,
                   val uemail:String? = null,
                   var uwagi:String? = null,
                   val lr1: Boolean?=null,
                   val lr2: Boolean?=null,
                   val lr3: Boolean?=null,
                   val lr4: Boolean?=null,
                   val lr5: Boolean?=null,
                   val lr6: Boolean?=null,
                   val lr7: Boolean?=null,
                   val lr8: Boolean?=null,
                   val lr9: Boolean?=null,
                   val lr9a: Boolean?=null,
                   val lr9b: Boolean?=null,
                   val lr9c: Boolean?=null,
                   val lr9d: Boolean?=null,
                   val lr10: Boolean?=null,
                   val lr11: Boolean?=null,
                   val lr12: Boolean?=null,
                   val lr13: Boolean?=null,
                   val lr14: Boolean?=null,
                   val lr15: Boolean?=null,
                   val lr16: Boolean?=null,
                   val lr17: Boolean?=null,
                   val lr18: Boolean?=null,
                   val lr21: Boolean?=null,
                   val lr22: Boolean?=null,
                   val lr23: Boolean?=null,
                   val lr24: Boolean?=null,
                   val lr25: Boolean?=null,
                   val lr26: Boolean?=null,
                   val lr27: Boolean?=null,
                   val lr28: Boolean?=null,
                   @DocumentId
                   val documentId: String? = null,
    ):Parcelable