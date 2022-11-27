package com.adilkhan.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import com.adilkhan.a7minutesworkout.databinding.ActivityExerciseBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
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
    private var currentPosition = 0

    // let's add text to speech in exercise
    // for this create tts
    private var tts : TextToSpeech? = null
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
        //initialize exercise list
        exerciseList = Constants.defaultExerciseList()
        //initialize tts
        tts = TextToSpeech(this,this)
        setUpRestTimerView()
    }
    private fun setUpRestTimerView()
    {
        // set visible to rest frame layout and tv title
        exerciseBinding?.flRestProgressBar?.visibility = View.VISIBLE
        exerciseBinding?.tvTitle?.visibility = View.VISIBLE
        exerciseBinding?.tvNextExercise?.visibility = View.VISIBLE
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
        exerciseBinding?.tvNextExercise?.text = "Upcoming: \n${exerciseList!![currentPosition].getName()}"
        speakOut("Take rest for 10 sec")
        setUpRestProgressBar()
    }
    private fun setUpExerciseView()
    {
        // set invisible to rest frame layout and tv title
        exerciseBinding?.flRestProgressBar?.visibility = View.INVISIBLE
        exerciseBinding?.tvTitle?.visibility = View.INVISIBLE
        exerciseBinding?.tvNextExercise?.visibility = View.INVISIBLE
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
        exerciseBinding?.ivImage?.setImageResource(exerciseList!![currentPosition-1].getImage())
        exerciseBinding?.tvExerciseTitle?.text = exerciseList!![currentPosition-1].getName()
        speakOut(exerciseList!![currentPosition-1].getName())
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

                // when exercise finish then call to rest
                if(currentPosition<exerciseList!!.size)
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
        if(tts!=null)
        {
            tts?.stop()
            tts?.shutdown()
        }

        exerciseBinding = null
    }
    // this method text to speech ke interface se implement hua hai
    override fun onInit(status: Int) {
         if(status==TextToSpeech.SUCCESS)
         {
             val result = tts!!.setLanguage(Locale.US)
             if(result == TextToSpeech.LANG_NOT_SUPPORTED || result==TextToSpeech.LANG_MISSING_DATA)
             {
                 Log.e("TTS", "Specified language not supported")
             }
         }
        else
         {
             Log.e("TTS", "initialization failed")
         }
    }
    // this method will speak whatever we will pass as a text
    private fun speakOut(text:String)
    {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}