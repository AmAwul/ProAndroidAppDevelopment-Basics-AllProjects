package com.awul.allclassxml

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.View
import android.widget.TextClock
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awul.allclassxml.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    private lateinit var tv_text_color: MaterialTextView

    private lateinit var bainding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
        bainding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bainding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tv_text_color = findViewById<MaterialTextView?>(R.id.tv_text_color).apply {
            setTextColor(Color.BLUE)
        }

//        tv_text_color.setTextColor(Color.BLUE)


        bainding.btnGone.setOnClickListener {
            bainding.tvViw.visibility = View.INVISIBLE
        }
        bainding.btnVisible.setOnClickListener {
            bainding.tvViw.visibility = View.VISIBLE
        }

        bainding.btnGetName.setOnClickListener {
            var name: String? = bainding.edtName.text?.toString() ?: "No Name enter ...!"


            bainding.tvShowName.text = "Your Typing Name is : $name"
        }

        bainding.btnToast.setOnClickListener {
            Toast.makeText(this,"This is a Toast",Toast.LENGTH_SHORT).show()
        }
        bainding.btnSnakbar.setOnClickListener {
            Snackbar.make(it,"this is snackbar",Snackbar.LENGTH_SHORT).apply {
                setAction("Click me"){
                    Toast.makeText(this@MainActivity,"This is a Toast",Toast.LENGTH_SHORT).show()
                }
            }.show()
        }





    }
}