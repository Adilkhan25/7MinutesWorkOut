package com.adilkhan.a7minutesworkout
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.adilkhan.a7minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class FinishActivity : AppCompatActivity() {
    private var finishBinding : ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finishBinding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(finishBinding?.root)
        setSupportActionBar(finishBinding?.finishToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        finishBinding?.finishToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }
        finishBinding?.btnFinish?.setOnClickListener {
            finish()
        }

        // Add record to the database
        val exerciseDao = (application as ExerciseApp).db.exerciseDao()
        addRecord(exerciseDao)
    }

    private fun addRecord(exerciseDao: ExerciseDao)
    {
        val calender = Calendar.getInstance()
        val dateTime = calender.time
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        lifecycleScope.launch{
            exerciseDao.insert(ExerciseEntity(date = date))
            Toast.makeText(this@FinishActivity,"History Added",Toast.LENGTH_SHORT).show()
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        if(finishBinding!=null)
        {
            finishBinding = null
        }
    }
}