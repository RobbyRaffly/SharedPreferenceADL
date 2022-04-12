package com.adl.sharedpreference

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference = getSharedPreferences("login_setting", MODE_PRIVATE)
        val username = sharedPreference.getString("username","")
        val Checked = sharedPreference.getBoolean("checbox", false)

        Username.setText(username)
        CheckBox.setChecked(Checked)

        btnLogIn.setOnClickListener{
            var editor = sharedPreference.edit()
            if(CheckBox.isChecked){
                editor.putString("username",Username.toString())
                editor.putBoolean("checkbox",true)
                editor.commit()
            }else{
                editor.remove("username")
                editor.commit()
            }
        }
    }
}