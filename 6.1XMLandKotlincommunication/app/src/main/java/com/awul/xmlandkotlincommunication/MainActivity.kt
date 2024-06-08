package com.awul.xmlandkotlincommunication

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awul.xmlandkotlincommunication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tvClick.setOnLongClickListener {
            Toast.makeText(this@MainActivity,"You Click on Click me Text",Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
        binding.btnSecond.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity,"You Click on Second Button",Toast.LENGTH_SHORT).show()

            }

        } )

//        binding.btnTuch.setOnTouchListener(object : OnTouchListener{
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//
//            }
//        })

        binding.btnTuch.setOnTouchListener { v, event ->
            when(event.action){

                MotionEvent.ACTION_DOWN -> {
                    Toast.makeText(this,"Motion Down",Toast.LENGTH_SHORT).show()
                }
                MotionEvent.ACTION_UP -> {
                Toast.makeText(this,"Motion Up",Toast.LENGTH_SHORT).show()
            }
            }
            return@setOnTouchListener true
        }

        var showImage : Boolean = false
        binding.btnSend.setOnClickListener {
            when (showImage){
                true ->{
                    binding.tvTitle.text = "Oh i see she is a Good Actor"
                    binding.imageView.visibility = View.VISIBLE
                    showImage = false
                }
                false ->{
                    binding.tvTitle.text = "This Grails has Gone"
                    binding.imageView.visibility = View.INVISIBLE
                    showImage = true
                }
            }


        }

        binding.edtTxt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Toast.makeText(this@MainActivity,"beforeTextChanged $s",Toast.LENGTH_SHORT).show()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Toast.makeText(this@MainActivity,"onChanged $s",Toast.LENGTH_SHORT).show()
            }

            override fun afterTextChanged(s: Editable?) {
                Toast.makeText(this@MainActivity,"After Changed $s",Toast.LENGTH_SHORT).show()
            }

        })




    }//onCreate

    fun clickMathod(view: View) {
        Toast.makeText(this,"You Click on First Button",Toast.LENGTH_SHORT).show()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        when(keyCode){
            KeyEvent.KEYCODE_BACK -> {
                Toast.makeText(this@MainActivity,"You Click on Back Button",Toast.LENGTH_SHORT).show()
            }
            KeyEvent.KEYCODE_A -> {
                Toast.makeText(this@MainActivity,"You Click on A Button",Toast.LENGTH_SHORT).show()
            }
            KeyEvent.KEYCODE_B -> {
                Toast.makeText(this@MainActivity,"You Click on B Button",Toast.LENGTH_SHORT).show()
            }
            KeyEvent.KEYCODE_C -> {
                Toast.makeText(this@MainActivity,"You Click on C Button",Toast.LENGTH_SHORT).show()
            }
            KeyEvent.KEYCODE_1 -> {
                Toast.makeText(this@MainActivity,"You Click on 1 Button",Toast.LENGTH_SHORT).show()
            }
        }

        

        return super.onKeyDown(keyCode, event)
    }
}