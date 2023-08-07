package com.stevenlop.loginsignupauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.stevenlop.loginsignupauth.databinding.ActivityLogin2Binding

class Login : AppCompatActivity() {

    private lateinit var binding:ActivityLogin2Binding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener{
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT ).show()
                    }
                }
            }else{
                Toast.makeText(this, "campos no pueden estar vacios", Toast.LENGTH_SHORT).show()
            }
        }
        binding.signupRedirectText.setOnClickListener{
            val signupIntent =Intent(this, SignupActivity::class.java)
            startActivity(signupIntent)
        }


    }
}