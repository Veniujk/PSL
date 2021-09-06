package com.example.helpdesk.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.helpdesk.BaseFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.example.helpdesk.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_sign_in.*

class LoginFragment: BaseFragment(){

   /* companion object {
        private const val RC_SIGN_IN = 120
    }*/
 //   private lateinit var auth: FirebaseAuth
    private val fbAuth = FirebaseAuth.getInstance()
    private val LOG_DEUBG = "LOG_DEBUG"
  //  private lateinit var googleSignInClient:GoogleSignInClient

    /* override fun onCreate(savedInstanceState: Bundle?){
         super.onCreate(savedInstanceState)
         // Configure Google Sign In
         val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            // .requestIdToken(getString(R.string.default_web_client_id))
             .requestIdToken("781251956342-i60mfd3n335bgh42h09m3pi6q1fh9hq0.apps.googleusercontent.com")
             .requestEmail()
             .build()

         googleSignInClient = GoogleSignIn.getClient(this, gso)

         auth = Firebase.auth
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginClick()
        setupRegistrationClick()
     //   signInGoogle()


    }

    private fun setupRegistrationClick() {
        signUpButton.setOnClickListener {
            findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment().actionId)
        }
    }
   /* private fun signInGoogle() {
        GoogleLoginButton.setOnClickListener{
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }*/
  /*  private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        fbAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    startApp()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                }
            }
    }*/
    private fun setupLoginClick() {
        loginButton.setOnClickListener {
            val email = emailLoginInput.text?.trim().toString()
            val pass = passLoginInput.text?.trim().toString()

            fbAuth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener { authRes ->
                    if(authRes.user != null) startApp()
                }
                .addOnFailureListener{ exc ->
                    Snackbar.make(requireView(), "Upss...Something went wrong...", Snackbar.LENGTH_SHORT)
                        .show()
                    Log.d(LOG_DEUBG, exc.message.toString())
                }
        }
    }
}