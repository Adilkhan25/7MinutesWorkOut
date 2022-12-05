package com.adilkhan.a7minutesworkout
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adilkhan.a7minutesworkout.databinding.ActivityFinishBinding


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
    }

    override fun onDestroy() {
        super.onDestroy()
        if(finishBinding!=null)
        {
            finishBinding = null
        }
    }
}