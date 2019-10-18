package com.example.datingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth?=null
    private var btnLogin:Button?=null
    private var editTxtEmail:EditText?=null
    private var editTxtPassword:EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        firebaseAuth = FirebaseAuth.getInstance()
        editTxtEmail = findViewById(R.id.editTxtEmail)
        editTxtPassword = findViewById(R.id.editTxtPassword)
        btnLogin = findViewById(R.id.btnLogin)
         btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin?.setOnClickListener {
            LoginUser()


        }
    }

    fun LoginUser(){

       var  editTxtEmail = editTxtEmail?.text.toString().trim()

        var  editTxtPassword = editTxtPassword?.text.toString().trim()


        if (TextUtils.isEmpty(editTxtEmail)){
            Toast.makeText(applicationContext,"please enter the email",Toast.LENGTH_SHORT).show()
        }

        else if (TextUtils.isEmpty(editTxtPassword)){
            Toast.makeText(applicationContext,"please enter the password",Toast.LENGTH_SHORT).show()
        }
        else{

            firebaseAuth?.signInWithEmailAndPassword(editTxtEmail,editTxtPassword)?.addOnCompleteListener(object:OnCompleteListener<AuthResult>{
                override fun onComplete(task: Task<AuthResult>) {

                    if (task.isSuccessful){

                        Toast.makeText(applicationContext, "Account Login Successfuly",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@Login, MainProfileOne::class.java))

                    }else{
                        var error = task.exception?.message
                        Toast.makeText(applicationContext, "Error"+error,Toast.LENGTH_SHORT).show()

                    }
                }


            })
        }




    }

    fun GOToSignup(view: View) {

        startActivity(Intent(this@Login,Signup::class.java))
    }
    

}
