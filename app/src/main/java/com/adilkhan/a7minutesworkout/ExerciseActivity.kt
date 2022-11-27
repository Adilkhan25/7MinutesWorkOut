package com.adilkhan.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.adilkhan.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var exerciseBinding: ActivityExerciseBinding? = null
    private var restTimer : CountDownTimer? = null
    private var restProcess : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseBinding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(exerciseBinding?.root)
        setSupportActionBar(exerciseBinding?.exerciseToolbar)
        if(supportActionBar!=null)
        {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseBinding?.exerciseToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }
        setUpTimerView()
    }
    private fun setUpTimerView()
    {
        if(restTimer!=null)
        {
            restTimer?.cancel()
            restTimer = null
            restProcess = 0
        }
        setUpProgressBar()
    }
    private fun setUpProgressBar()
    {
        exerciseBinding?.progressBar?.progress = restProcess
        restTimer = object : CountDownTimer(10000, 1000)
        {
            override fun onTick(p0: Long) {
                restProcess++
              exerciseBinding?.progressBar?.progress = 10-restProcess
                exerciseBinding?.tvTimer?.text = (p0/1000).toString()

            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "Timer finished", Toast.LENGTH_LONG).show()
            }

        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null)
        {
            restTimer?.cancel()
            restTimer = null
        }
        exerciseBinding = null
    }
}