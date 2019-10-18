package com.example.datingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        var profile_img = findViewById<ImageView>(R.id.profile_img)

        profile_img.setOnClickListener(View.OnClickListener {

          var intent = Intent(this@Profile,Tapped::class.java)
            startActivity(intent)
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_item,menu)
        return true
    }


}
