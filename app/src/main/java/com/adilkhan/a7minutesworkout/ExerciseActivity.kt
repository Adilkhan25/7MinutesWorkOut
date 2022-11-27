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

    // create exercise list and current position of exercise
    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentPosition = -1
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
        exerciseList = Constants.defaultExerciseList()
        setUpRestTimerView()
    }
    private fun setUpRestTimerView()
    {
        // set visible to rest frame layout and tv title
        exerciseBinding?.flRestProgressBar?.visibility = View.VISIBLE
        exerciseBinding?.tvTitle?.visibility = View.VISIBLE
        // set invisible to image view , text view fl exercise
        exerciseBinding?.flExerciseProgressBar?.visibility = View.INVISIBLE
        exerciseBinding?.tvExerciseTitle?.visibility = View.INVISIBLE
        exerciseBinding?.ivImage?.visibility = View.INVISIBLE
        if(restTimer!=null)
        {
            restTimer?.cancel()
            restTimer = null
            restProcess = 0
        }
        setUpRestProgressBar()
    }
    private fun setUpExerciseView()
    {
        // set invisible to rest frame layout and tv title
        exerciseBinding?.flRestProgressBar?.visibility = View.INVISIBLE
        exerciseBinding?.tvTitle?.visibility = View.INVISIBLE
        // set visible to image view , text view fl exercise
        exerciseBinding?.flExerciseProgressBar?.visibility = View.VISIBLE
        exerciseBinding?.tvExerciseTitle?.visibility = View.VISIBLE
        exerciseBinding?.ivImage?.visibility = View.VISIBLE
        if(exerciseTimer!=null)
        {
            exerciseTimer?.cancel()
            exerciseTimer = null
            exerciseProcess = 0
        }
        // actual image and text from exercise model to xml
        exerciseBinding?.ivImage?.setImageResource(exerciseList!![currentPosition].getImage())
        exerciseBinding?.tvExerciseTitle?.text = exerciseList!![currentPosition].getName()
        setUpExerciseProgressBar()
    }
    private fun setUpRestProgressBar()
    {
        exerciseBinding?.restProgressBar?.progress = restProcess
        restTimer = object : CountDownTimer(10000, 1000)
        {
            override fun onTick(p0: Long) {
                restProcess++
              exerciseBinding?.restProgressBar?.progress = 10-restProcess
                exerciseBinding?.tvTimer?.text = (p0/1000).toString()

            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "10 finished, let's do some exercise", Toast.LENGTH_LONG).show()
                // when rest finish then call to exercise
                currentPosition++
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
                        " rest",Toast.LENGTH_LONG).show()
                // when exercise finish then call to rest
                if(currentPosition<exerciseList!!.size - 1)
                    setUpRestTimerView()
                else
                {
                    Toast.makeText(this@ExerciseActivity,"Wow you have done 7 minute work out",
                    Toast.LENGTH_LONG).show()
                }
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