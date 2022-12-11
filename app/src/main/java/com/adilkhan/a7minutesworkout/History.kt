package com.adilkhan.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adilkhan.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class History : AppCompatActivity() {
    private var historyBinding : ActivityHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyBinding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(historyBinding?.root)
        setSupportActionBar(historyBinding?.historyToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        historyBinding?.historyToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }
        supportActionBar?.title = "History"
        val exerciseDao = (application as ExerciseApp).db.exerciseDao()
        lifecycleScope.launch{
            exerciseDao.fetchAllExercise().collect{
                if(it!=null)
                {
                    setUpExerciseHistoryRecyclerView(ArrayList(it))
                }
            }
        }


    }
    private fun setUpExerciseHistoryRecyclerView(exerciseList:ArrayList<ExerciseEntity>)
    {
        if(exerciseList.isNotEmpty())
        {
            val adapter = HistoryAdapter(exerciseList)
            historyBinding?.rvHistory?.layoutManager = LinearLayoutManager(this@History)
            historyBinding?.rvHistory?.adapter = adapter
        }
    }
}