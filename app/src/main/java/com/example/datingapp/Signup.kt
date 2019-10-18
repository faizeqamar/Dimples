package com.example.datingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*

class Signup : AppCompatActivity() {

    private var etName:EditText?=null
    private var etEmail:EditText?=null
    private var etPhonNmbr:EditText?=null
    private var et_age:EditText?=null
    private var et_gender:EditText?=null
    private var etPassword:EditText?=null
    private var btnSignUp:Button?=null
    private var firebaseAuth: FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPhonNmbr = findViewById(R.id.etPhonNmbr)
        et_age = findViewById(R.id.et_age)
        et_gender = findViewById(R.id.et_gender)
        etPassword = findViewById(R.id.etPassword)
        firebaseAuth = FirebaseAuth.getInstance()
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp?.setOnClickListener{

            SignupUser()

    }
    }

    fun SignupUser(){

        val etName = etName?.text.toString().trim()
        val etEmail = etEmail?.text.toString().trim()
        val etPhonNmbr = etPhonNmbr?.text.toString().trim()
        val et_age = et_age?.text.toString().trim()
        val et_gender = et_gender?.text.toString().trim()
        val etPassword = etPassword?.text.toString().trim()

        if (TextUtils.isEmpty(etName)){
            Toast.makeText(applicationContext, "enter the name",Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(etEmail)){
            Toast.makeText(applicationContext, "enter the email",Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(etPhonNmbr)){
            Toast.makeText(applicationContext, "enter the Phonnumber",Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(et_age)){
            Toast.makeText(applicationContext, "enter the age",Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(et_gender)){
            Toast.makeText(applicationContext, "enter the gander",Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(etPassword)){
            Toast.makeText(applicationContext, "enter the password",Toast.LENGTH_SHORT).show()
        }
        else{

            firebaseAuth?.createUserWithEmailAndPassword(etEmail,etPassword)?.addOnCompleteListener(object :OnCompleteListener<AuthResult>{
                override fun onComplete(task: Task<AuthResult>) {
                    if (task.isSuccessful){

                        Toast.makeText(applicationContext, "account is created",Toast.LENGTH_SHORT).show()
                        var user:FirebaseUser = firebaseAuth!!.currentUser!!

                        user.sendEmailVerification().addOnCompleteListener(object : OnCompleteListener<Void>{
                            override fun onComplete(task: Task<Void>) {

                                if (task.isSuccessful) {
                                    startActivity(Intent(this@Signup, LR_Activity::class.java))

                                } else{

                                    val error = task.exception?.message
                                    Toast.makeText(applicationContext, "Error"+error,Toast.LENGTH_SHORT).show()
                                }
                            }


                        })
                    }
                }

            })
        }
    }

}
