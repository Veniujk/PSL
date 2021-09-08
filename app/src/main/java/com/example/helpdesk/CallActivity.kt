package com.example.helpdesk

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.helpdesk.R
import com.example.helpdesk.raport.RaportFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView


class CallActivity: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCall()
    }
    fun onCall() {
        val permissionCheck =
            context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.CALL_PHONE) }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(Manifest.permission.CALL_PHONE),
                    123
                )
            }
        } else {
            startActivity(Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:412010998")))
            findNavController()
                .navigate(CallActivityDirections.actionAfterCalltoProfil().actionId)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            123 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onCall()
            } else {
                Log.d("TAG", "Call Permission Not Granted")
            }
            else -> {
            }
        }
    }
}