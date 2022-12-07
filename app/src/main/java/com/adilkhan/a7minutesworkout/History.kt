package com.adilkhan.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adilkhan.a7minutesworkout.databinding.ActivityHistoryBinding

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

    }
}