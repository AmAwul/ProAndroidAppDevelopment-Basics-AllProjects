package com.awul.activitytoactivitydatapass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btn_next: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        btn_next = findViewById(R.id.btn_next)

        btn_next.setOnClickListener {
            var i : Intent = Intent(this@MainActivity,SecondActivity::class.java).apply {
                this.putExtra("name","My Name is Mr.Awul \n\n I`m a new Kotlin Developer \n\n" +
                        "    Strong knowledge of the Kotlin programming language and Android or backend development\n" +
                        "    Familiarity with software development principles and design patterns\n" +
                        "    Experience with software development tools such as Git, Jira, and continuous integration systems\n" +
                        "    Familiarity with databases such as MySQL or PostgreSQL\n" +
                        "    Strong problem-solving skills\n" +
                        "    Excellent communication skills")

            }

            startActivity(i)
        }

    }
}