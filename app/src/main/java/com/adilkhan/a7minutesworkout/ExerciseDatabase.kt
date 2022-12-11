package com.adilkhan.a7minutesworkout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExerciseEntity::class], version = 1)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao():ExerciseDao
    companion object
    {
        @Volatile
        private var INSTANCE : ExerciseDatabase? = null
        fun getInstance(context: Context):ExerciseDatabase
        {

            synchronized(this)
            {   var instance = INSTANCE
                if(instance==null)
                {
                    instance = Room.databaseBuilder(context.applicationContext,
                    ExerciseDatabase::class.java,"Exercise_Database").fallbackToDestructiveMigration()
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }

}