package com.example.datingapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main_profile_two.view.*

class MainProfileTwo : AppCompatActivity() {

    //************** Declaration**************

    var et_children:EditText?=null
    var et_city:EditText?=null
    var btn_submit:Button?=null
    var relation_spinner:Spinner?=null
    var relationText:TextView?=null
    var education_spinner:Spinner?=null
    var educationText:TextView?=null
    var firebaseAuth: FirebaseAuth?=null
    var firebaseDatabase: DatabaseReference?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_profile_two)

        //************** Initialization**************

        relation_spinner = findViewById(R.id.relation_spinner)
        education_spinner = findViewById(R.id.education_spinner)
        et_children = findViewById(R.id.et_children)
        et_city = findViewById(R.id.et_city)
        btn_submit = findViewById(R.id.btn_submit)

        //************** Arrays**************


        //here we will create array of relation spinner
        val relationArray = arrayOf("Select one", "Friend", "Wife", "Non")

        //here we will create array of relation spinner
        val educationArray = arrayOf("Select one", "Metric", "Enter","BS","MS","PHD","Non")

        //************** Adaptors**************


        //here we will create adapter for height spinner
        var adaptor = ArrayAdapter(this,R.layout.coloue_spinner_layout,relationArray)
        adaptor.setDropDownViewResource(R.layout.spinner_dropdown)
        relation_spinner?.adapter=adaptor
        //here we will create adapter for quality spinner
        adaptor = ArrayAdapter(this,R.layout.coloue_spinner_layout,educationArray)
        adaptor.setDropDownViewResource(R.layout.spinner_dropdown)
        education_spinner?.adapter = adaptor


        //************** Listener**************

        //here we will set Listner on relation spinner
        relation_spinner?.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {

                val relationText = parent?.getItemAtPosition(position) as String

                firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Relation")

                firebaseDatabase?.child("User Relation")?.setValue(relationText)

                updateHeight()
            }

            fun updateHeight(){

                val relationText = relationText?.text.toString()
                var relationInfo = HashMap<String,String>()
                relationInfo.put("relationText",relationText)

                firebaseDatabase?.updateChildren(relationInfo as Map<String,String>)?.addOnCompleteListener(object:
                    OnCompleteListener<Void> {
                    override fun onComplete(task: Task<Void>) {

                        if (task.isSuccessful){
                            Toast.makeText(applicationContext, "Height updated",Toast.LENGTH_SHORT).show()
                        }else{

                            var error = task.exception?.message

                            Toast.makeText(applicationContext, "error: "+error,Toast.LENGTH_SHORT).show()
                        }
                    }


                })
            }

        }

        //here we will set Listner on education spinner

        education_spinner?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {

                var educationText = parent?.getItemAtPosition(position) as String
                firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Education")
                firebaseDatabase?.child("User Education")?.setValue(educationText)

                //here we call function
                updataQuality()
            }

            fun updataQuality(){
                val educationText = educationText?.text.toString()
                val educationInfo = HashMap<String,String>()
                educationInfo.put("educationText",educationText)
                firebaseDatabase?.updateChildren(educationInfo as Map<String,String>)?.addOnCompleteListener(object:
                    OnCompleteListener<Void> {
                    override fun onComplete(task: Task<Void>) {

                        if (task.isSuccessful){
                            Toast.makeText(applicationContext,"Quality updated",Toast.LENGTH_SHORT).show()
                        }else{
                            var error = task.exception?.message
                            Toast.makeText(applicationContext, "error : "+error,Toast.LENGTH_SHORT).show()

                        }
                    }


                })

            }


        }


        btn_submit?.setOnClickListener {

            updateChoildrenAndCity()

        }

    }

    fun updateChoildrenAndCity(){

        val et_children = et_children?.text.toString().trim()
        val et_city = et_city?.text.toString().trim()

        if (TextUtils.isEmpty(et_children)){
            Toast.makeText(applicationContext, "Enter your children",Toast.LENGTH_SHORT).show()
        }else if (TextUtils.isEmpty(et_city)){
            Toast.makeText(applicationContext, "Enter your city",Toast.LENGTH_SHORT).show()
        }else{


            var userInfo = HashMap<String,Any>()
            userInfo.put("et_children",et_children)
            userInfo.put("et_city",et_city)
            firebaseDatabase?.updateChildren(userInfo)?.addOnCompleteListener(object :OnCompleteListener<Void>{
                override fun onComplete(task: Task<Void>) {

                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "updated successfuly",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainProfileTwo,Profile::class.java))


                    }else{
                        var error = task.exception?.message
                        Toast.makeText(applicationContext, "error: "+error,Toast.LENGTH_SHORT).show()


                    }
                }

            })
        }



    }
}
