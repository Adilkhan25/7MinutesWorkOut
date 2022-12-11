package com.adilkhan.a7minutesworkout

import android.app.Application

class ExerciseApp:Application() {
    val db by lazy {
        ExerciseDatabase.getInstance(this)
    }
}