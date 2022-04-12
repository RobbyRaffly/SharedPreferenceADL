package com.adl.sharedpreference

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.*


class Settings : AppCompatActivity() {


    var sliderValue:Int = 0
    var groupCombo:Int = 0
    var switchComp:Boolean = false
    var switcherComp:Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val sharedPreference = getSharedPreferences("login_setting_app", MODE_PRIVATE)
        var editor = sharedPreference.edit()

        seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                count.setText("${p1.toString()}")
                seekBar1.setProgress(p1)
                sliderValue=p1

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?){
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        seekBar1.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                count.setText("${p1.toString()}")
                seekBar.setProgress(p1)
                sliderValue=p1

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?){
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        radioGroup.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                var resultt = when(checkedId){
                  R.id.radioButtonOne -> "Option 1"
                  R.id.radioButtonTwo -> "Option 2"
                  R.id.radioButtonThree -> "Option 3"
                  else-> "No Option"
                }
                groupCombo=checkedId
                Toast.makeText(this@Settings,"Option yang dipilih adalah ${resultt}",Toast.LENGTH_LONG).show()
            }
        })

//        switchOn.setOnClickListener{
//            if(switchOn.isChecked){
//                switchOn.setText("On")
//            }else{
//                switchOn.setText("Off")
//
//            }
//        }
//        switcher.setOnCheckedChangeListener {object :CompoundButton.OnCheckedChangeListener{
//            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//                switcherComp=isChecked
//                Toast.makeText(this@Settings,"nilainya adalah ${isChecked}",Toast.LENGTH_SHORT).show()
//                switcher.setChecked(isChecked)
//            }
//            }
//        }

        switcher.setOnCheckedChangeListener { checked ->
            if (checked) {
                switchComp=checked
                switchOn.setChecked(checked)
            }else{
                switchOn.setChecked(checked)
                switchComp=checked
            }
        }

        switchOn.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                Toast.makeText(this@Settings,"nilainya adalah ${p1}",Toast.LENGTH_SHORT).show()
                switcher.setChecked(p1)
                switchComp = p1
                switcherComp = p1
                if(p1==true){
                    switchOn.setText("ON")
                }else{
                    switchOn.setText("OFF")
                }
            }
        })

        btnSave.setOnClickListener({
            editor.putInt("slider",sliderValue)
            editor.putInt("combo",groupCombo)
            editor.putBoolean("switch",switchComp)
            editor.putString("text",editTxtket.text.toString())
            editor.putBoolean("switcer", switcherComp)
            editor.commit()

        })

        btnClear.setOnClickListener{
            editor.remove("slider")
            editor.remove("combo")
            editor.remove("switch")
            editor.remove("text")
            editor.remove("switcer")
            editor.commit()
        }

        loadSave(sharedPreference)


    }

    private fun loadSave(sharedPreference: SharedPreferences) {
        sliderValue = sharedPreference.getInt("slider",0)
        groupCombo = sharedPreference.getInt("combo",0)
        switchComp = sharedPreference.getBoolean("switch",false)
        editTxtket.setText(sharedPreference.getString("text",""))
        seekBar1.setProgress(sliderValue)
        seekBar.setProgress(sliderValue)
        radioGroup.check(groupCombo)
        switchOn.setChecked(switchComp)
        switcher.setChecked(switcherComp)

    }
}