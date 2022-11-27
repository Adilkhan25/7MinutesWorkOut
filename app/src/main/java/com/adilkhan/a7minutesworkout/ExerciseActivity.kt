package com.adilkhan.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.adilkhan.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var exerciseBinding: ActivityExerciseBinding? = null
    // this countDown Time for rest
    private var restTimer : CountDownTimer? = null
    private var restProcess : Int = 0
    // this countDown time for exercise
    // create two method for exercise time as you have created for rest time
    // and reset it in onDestroy method
    private var exerciseTimer : CountDownTimer? = null
    private var exerciseProcess : Int = 0
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
        // set invisible to exercise frame layout and visible to rest frame layout
        exerciseBinding?.flExerciseProgressBar?.visibility = View.INVISIBLE
        exerciseBinding?.flProgressBar?.visibility = View.VISIBLE
        // change title from continue do  to get ready for
        exerciseBinding?.tvTitle?.text = "Get ready for"
        if(restTimer!=null)
        {
            restTimer?.cancel()
            restTimer = null
            restProcess = 0
        }
        setUpProgressBar()
    }
    private fun setUpExerciseView()
    {
        // set visible to exercise frame layout and invisible to rest frame layout
        exerciseBinding?.flExerciseProgressBar?.visibility = View.VISIBLE
        exerciseBinding?.flProgressBar?.visibility = View.INVISIBLE
        // change title from get ready for to continue do
        exerciseBinding?.tvTitle?.text = "continue do"
        if(exerciseTimer!=null)
        {
            exerciseTimer?.cancel()
            exerciseTimer = null
            exerciseProcess = 0
        }
        setUpExerciseProgressBar()
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
                Toast.makeText(this@ExerciseActivity, "10 finished, let's do some exercise", Toast.LENGTH_LONG).show()
                // when rest finish then call to exercise
                setUpExerciseView()
            }

        }.start()

    }
    private fun setUpExerciseProgressBar()
    {
        exerciseBinding?.exerciseProgressBar?.progress = exerciseProcess
        exerciseTimer=object : CountDownTimer(30000,1000)
        {
            override fun onTick(p0: Long) {
                exerciseProcess++
                exerciseBinding?.exerciseProgressBar?.progress = 30-exerciseProcess
                exerciseBinding?.tvExerciseTimer?.text = (p0/1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "30 second finished now let's go for" +
                        "rest",Toast.LENGTH_LONG).show()
                // when exercise finish then call to rest
              //  setUpTimerView()
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
        if(exerciseTimer!=null)
        {
            exerciseTimer?.cancel()
            exerciseTimer = null
        }

        exerciseBinding = null
    }
}