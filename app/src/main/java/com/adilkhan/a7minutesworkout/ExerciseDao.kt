package com.adilkhan.a7minutesworkout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insert(exerciseEntity: ExerciseEntity)
    @Query ("SELECT *FROM 'Exercise_Entity'")
    fun fetchAllExercise():Flow<List<ExerciseEntity>>
    // id = date
  //  @Query ("SELECT *FROM 'exercise_entity' WHERE Id=:id")
  //  fun fetchExerciseById(id:String):Flow<ExerciseEntity>

}