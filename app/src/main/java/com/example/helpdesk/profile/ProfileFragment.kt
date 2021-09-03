package com.example.helpdesk.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.helpdesk.BaseFragment
import com.example.helpdesk.R
import com.example.helpdesk.data.Raport
import com.example.helpdesk.data.User
import com.example.helpdesk.home.OnRaportItemLongClick
import com.example.helpdesk.home.RaportAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.ByteArrayOutputStream
import java.lang.Exception

class ProfileFragment : BaseFragment(), OnRaportItemLongClick {
    private val PROFILE_DEBUG = "PROFILE_DEBUG"
    private val REQUEST_IMAGE_CAPTURE = 1
    private val auth = FirebaseAuth.getInstance()
    private val profileVm by viewModels<ProfileViewModel>()
    private val adapter = RaportAdapter(this)
    private val cloud = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout_action -> {
                auth.signOut()
                requireActivity().finish()
            }
        }
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSubmitDataClick()
        setupTakePictureClick()
        recycleruserRaport.layoutManager = LinearLayoutManager(requireContext())
        recycleruserRaport.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        profileVm.user.observe(viewLifecycleOwner, {user ->
            bindUserData(user)
        })

        profileVm.userRaports.observe(viewLifecycleOwner, { list ->
            list?.let {
              adapter.setRaports(list.sortedByDescending { it.date })
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap

            Log.d(PROFILE_DEBUG, "BITMAP: " + imageBitmap.byteCount.toString())

            Glide.with(this)
                    .load(imageBitmap)
                    .circleCrop()
                    .into(userImage)

            val stream = ByteArrayOutputStream()
            val result = imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()

            if(result) profileVm.uploadUserPhoto(byteArray)
        }
    }

    private fun setupTakePictureClick(){
        userImage.setOnClickListener {
            takePicture()
        }
    }
    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
        try {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }catch (exc: Exception){
            Log.d(PROFILE_DEBUG, exc.message.toString())
        }
    }
    private fun bindUserData(user: User) {
        Log.d(PROFILE_DEBUG,user.toString())
        userNameET.setText(user.name)
        userSurnameET.setText(user.surname)
        userEmailET.setText(user.email)
        Glide.with(this)
                .load(user.image)
                .circleCrop()
                .into(userImage)
    }
    private fun setupSubmitDataClick(){
        submitDataProfile.setOnClickListener {
            val name = userNameET.text.trim().toString()
            val surname = userSurnameET.text.trim().toString()
            val map = mapOf("name" to name, "surname" to surname)
            profileVm.editProfileData(map)
            Snackbar.make(requireView(), "Zaktualizowano dane!", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun onRaportLongClick(raport: Raport, position: Int) {
        val bundle = bundleOf("raport" to raport)
                findNavController()
                    .navigate(ProfileFragmentDirections.actionProfileFragmentToRaportFragment().actionId,bundle)}



    }
