package com.awul.activitytoactivitydatapass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    lateinit var btn_bac: Button
    lateinit var btn_data: Button
    lateinit var tv_name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn_bac = findViewById(R.id.btn_bac)
        btn_data = findViewById(R.id.btn_data)
        tv_name = findViewById(R.id.tv_name)


        val gname: String = intent?.getStringExtra("name") ?: "No Data Input"

        btn_data.setOnClickListener {

            tv_name.text = gname
        }



        btn_bac.setOnClickListener {
            val i: Intent = Intent(this@SecondActivity,MainActivity::class.java)
            startActivity(i)
        }


    }
}