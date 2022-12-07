package com.adilkhan.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adilkhan.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.flStart?.setOnClickListener{
            val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
          //  finish()
        }
        // for bmi calculation
        binding?.flBmi?.setOnClickListener{
            val bmiIntent = Intent(this@MainActivity, BMIActivity::class.java)
            startActivity(bmiIntent)
        }
        // for history
        binding?.flHistory?.setOnClickListener {
            val historyIntent = Intent(this@MainActivity, History::class.java)
            startActivity(historyIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}