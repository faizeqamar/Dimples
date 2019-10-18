package com.example.datingapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.datingapp.MainProfileOne as MainProfileOne


class MainProfileOne : AppCompatActivity() {

    //************** Declaration**************

    //here we will create variables
    var image_view:ImageView?=null
    var et_height:EditText?=null
    var btn_save:Button?=null
    var Quality_spinner:Spinner?=null
    var qualityText:TextView?=null
    var interest_spinner:Spinner?=null
    var interestText:TextView?=null
    var status_spinner:Spinner?=null
    var statusText:TextView?=null
    var firebaseAuth:FirebaseAuth?=null
    var firebaseDatabase:DatabaseReference?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_profile_one)

        //************** Initialization**************

        //here we will initialiaz ImageView
        image_view = findViewById(R.id.image_view)
        //here we will initialiaz variables for height
        et_height = findViewById(R.id.et_height)
        btn_save = findViewById(R.id.btn_save)
        firebaseAuth = FirebaseAuth.getInstance()
        //here we will initialiaz variables for quality
        Quality_spinner = findViewById(R.id.Quality_spinner)
        //here we will initialiaz variables for interest
        interest_spinner = findViewById(R.id.interest_spinner)
        //here we will initialiaz variables for status
        status_spinner = findViewById(R.id.status_spinner)

        //************** Arrays**************

        //here we will create Array for height spinner
        val qualityArray = arrayOf("Select One","Alcoholic","Smoking","Non")
        //here we will create Array for interest spinner
        val interestArray = arrayOf("Select One","Friendship","Sex")
        //here we will create Array for status spinner
        val statusArray = arrayOf("Select One","Single","Marid")



        //************** Adaptors**************



        //here we will create adapter for quality spinner
       var adaptor = ArrayAdapter(this,R.layout.coloue_spinner_layout,qualityArray)
        adaptor.setDropDownViewResource(R.layout.spinner_dropdown)
        Quality_spinner?.adapter = adaptor
        //here we will create adapter for quality spinner
        adaptor = ArrayAdapter(this,R.layout.coloue_spinner_layout,interestArray)
        adaptor.setDropDownViewResource(R.layout.spinner_dropdown)
        interest_spinner?.adapter = adaptor
        //here we will create adapter for status spinner
        adaptor = ArrayAdapter(this,R.layout.coloue_spinner_layout,statusArray)
        adaptor.setDropDownViewResource(R.layout.spinner_dropdown)
        status_spinner?.adapter = adaptor



        //************** Listener**************


        //here we will set Listner on quality spinner

        Quality_spinner?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {

                var qualityText = parent?.getItemAtPosition(position) as String
                firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Qualities")
                firebaseDatabase?.child("UserQuality")?.setValue(qualityText)

                //here we call function
                updataQuality()
            }

            fun updataQuality(){
                val qualityText = qualityText?.text.toString()
                val qualityInfo = HashMap<String,String>()
                qualityInfo.put("qualityText",qualityText)
                firebaseDatabase?.updateChildren(qualityInfo as Map<String,String>)?.addOnCompleteListener(object:OnCompleteListener<Void>{
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

        //here we will set Listner on Interst spinner

        interest_spinner?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {

                var interestText = parent?.getItemAtPosition(position) as String
                firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Interest")
                firebaseDatabase?.child("User Interest")?.setValue(interestText)

                //here we will call updadeInterest function

                updateInterest()
            }

            fun updateInterest() {

                var interestText = interestText?.text.toString()
                var interestInfo = HashMap<String,String>()
                interestInfo.put("interestText",interestText)
                firebaseDatabase?.updateChildren(interestInfo as Map<String,String>)?.addOnCompleteListener(object :OnCompleteListener<Void>{
                    override fun onComplete(task: Task<Void>) {
                        if(task.isSuccessful){
                            Toast.makeText(applicationContext, "Interest updated",Toast.LENGTH_SHORT).show()
                        }else{
                            var error = task.exception?.message
                            Toast.makeText(applicationContext, "error :"+error,Toast.LENGTH_SHORT).show()
                        }
                    }

                })
            }

        }

        //here we will set Listner on status spinner

        status_spinner?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {

                var statusText = parent?.getItemAtPosition(position) as String
                firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Interest")
                firebaseDatabase?.child("User Interest")?.setValue(statusText)

                //here we will call updadeInterest function

                updateStatus()
            }

            fun updateStatus() {

                var statusText = interestText?.text.toString()
                var statustInfo = HashMap<String,String>()
                statustInfo.put("statusText",statusText)
                firebaseDatabase?.updateChildren(statustInfo as Map<String,String>)?.addOnCompleteListener(object :OnCompleteListener<Void>{
                    override fun onComplete(task: Task<Void>) {
                        if(task.isSuccessful){
                            Toast.makeText(applicationContext, "Status updated",Toast.LENGTH_SHORT).show()
                        }else{
                            var error = task.exception?.message
                            Toast.makeText(applicationContext, "error :"+error,Toast.LENGTH_SHORT).show()
                        }
                    }

                })
            }

        }

        //*************Save Button Work*************

        btn_save?.setOnClickListener {
            updateUserHeight()


        }


        //************** Work on changing Profile**************


        image_view?.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                }
                else{
                    //permission already granted
                    pickImageFromGallery()
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000
        //Permission code
        private val PERMISSION_CODE = 1001
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            image_view?.setImageURI(data?.data)
        }





    }







    //****************EditText Work***************

    fun updateUserHeight(){

        val et_height = et_height?.text.toString().trim()

        if (TextUtils.isEmpty(et_height)){
            Toast.makeText(applicationContext, "Enter your Height",Toast.LENGTH_SHORT).show()
        }else{

            var userHeight = HashMap<String,Any>()
            userHeight.put("et_height",et_height)
            firebaseDatabase?.updateChildren(userHeight)?.addOnCompleteListener(object :OnCompleteListener<Void>{
                override fun onComplete(task: Task<Void>) {

                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "updated successfuly",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainProfileOne,MainProfileTwo::class.java))


                    }else{
                        var error = task.exception?.message
                        Toast.makeText(applicationContext, "error: "+error,Toast.LENGTH_SHORT).show()


                    }
                }

            })
        }



    }
}






