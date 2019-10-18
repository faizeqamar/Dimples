package com.example.datingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*

class LR_Activity : AppCompatActivity() {
    private var etLR_Password:EditText?=null
    private var etLR_Email:EditText?=null
    private var btnLR_Login:Button?=null
    private var tvLR_ForgatPassword:TextView?=null
    private var firebaseAuth:FirebaseAuth?=null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lr_)
        firebaseAuth = FirebaseAuth.getInstance()

        etLR_Password =findViewById(R.id.etLR_Password)
        etLR_Email =findViewById(R.id.etLR_Email)

        btnLR_Login =findViewById(R.id.btnLR_Login)
        tvLR_ForgatPassword =findViewById(R.id.tvLR_ForgatPassword)


        btnLR_Login?.setOnClickListener{

            SignIn()

        }
//
//        tvLR_ForgatPassword?.setOnClickListener {
//            ResetPasswordUser()
//
//        }

    }

    fun SignIn(){
        var etLR_Email = etLR_Email?.text.toString().trim()
        var etLR_Password =etLR_Password?.text.toString().trim()

        if (TextUtils.isEmpty(etLR_Email)){

            Toast.makeText(applicationContext, "please enter the email",Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(etLR_Password)){

            Toast.makeText(applicationContext, "please enter the password",Toast.LENGTH_SHORT).show()
        }else{

            firebaseAuth?.signInWithEmailAndPassword(etLR_Email,etLR_Password)?.addOnCompleteListener(object:OnCompleteListener<AuthResult>{
                override fun onComplete(task: Task<AuthResult>) {
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "Welcome To Your Account",Toast.LENGTH_SHORT).show()

                        // verifecation
                        var user:FirebaseUser = firebaseAuth?.currentUser!!

                        if (user.isEmailVerified){

                            Toast.makeText(applicationContext, "your login successfully",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LR_Activity,MainProfileOne::class.java))
                        }
                        else{
                            var error = task.exception?.message
                            Toast.makeText(applicationContext, "account is not verified",Toast.LENGTH_SHORT).show()

                        }
                    }
                    else{
                        var error= task.exception?.message
                        Toast.makeText(applicationContext, "Something went wrong",Toast.LENGTH_SHORT).show()

                    }
                }


            })
        }



    }


//    fun ResetPasswordUser(){
//
//
//
//        var etLR_Email = etLR_Email?.text.toString().trim()
//        var  etLR_Password = etLR_Password?.text.toString().trim()
//
//        if (TextUtils.isEmpty(etLR_Email)){
//
//            Toast.makeText(applicationContext, "enter email",Toast.LENGTH_SHORT).show()
//        } else{
//            firebaseAuth?.sendPasswordResetEmail(etLR_Email)?.addOnCompleteListener(object:OnCompleteListener<Void> {
//                override fun onComplete(task: Task<Void>) {
//
//                    if (task.isSuccessful){
//                        Toast.makeText(applicationContext, "check your email",Toast.LENGTH_SHORT).show()
//                    }
//
//                    else{
//                        var error = task.exception?.message
//                        Toast.makeText(applicationContext, "email is not found"+error,Toast.LENGTH_SHORT).show()
//
//                    }
//
//                }
//
//
//            })
//
//        }
//
//
//
//
//    }
}
