package com.adilkhan.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adilkhan.a7minutesworkout.databinding.ActivityBmiactivityBinding
import com.adilkhan.a7minutesworkout.databinding.ActivityMainBinding

class BMIActivity : AppCompatActivity() {
    private var bmiBinding:ActivityBmiactivityBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bmiBinding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(bmiBinding?.root)
        setSupportActionBar(bmiBinding?.bmiToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bmiBinding?.bmiToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }

    }
}