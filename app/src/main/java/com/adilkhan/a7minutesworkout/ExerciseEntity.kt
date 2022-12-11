package com.adilkhan.a7minutesworkout

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercise_Entity")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = false)
    val date:String = ""
    )

