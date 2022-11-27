package com.adilkhan.a7minutesworkout

object Constants {
    fun defaultExerciseList():ArrayList<ExerciseModel>{
        val  exerciseList = ArrayList<ExerciseModel>()
        val lunge = ExerciseModel(1,"Lunge",
        R.drawable.ic_lunge, isComplete = false, isSelected = false)
        exerciseList.add(lunge)
        val plank = ExerciseModel(2,"Plank",
            R.drawable.ic_plank, isComplete = false, isSelected = false)
        exerciseList.add(plank)
        val pushUp = ExerciseModel(3,"Push Up",
            R.drawable.ic_push_up, isComplete = false, isSelected = false)
        exerciseList.add(pushUp)
        val pushUpAndRotation = ExerciseModel(4,"Push Up and Rotation",
            R.drawable.ic_push_up_and_rotation, isComplete = false, isSelected = false)
        exerciseList.add(pushUpAndRotation)
        val sidePlank = ExerciseModel(5,"Side Plank",
            R.drawable.ic_side_plank, isComplete = false, isSelected = false)
        exerciseList.add(sidePlank)
        val squat = ExerciseModel(6,"Squat",
            R.drawable.ic_squat, isComplete = false, isSelected = false)
        exerciseList.add(squat)
        val stepUpOntoChair = ExerciseModel(7,"Step Up onto Chair",
            R.drawable.ic_step_up_onto_chair, isComplete = false, isSelected = false)
        exerciseList.add(stepUpOntoChair)
        val tricepsDipOnChair = ExerciseModel(8,"Triceps Dip On Chair",
            R.drawable.ic_triceps_dip_on_chair, isComplete = false, isSelected = false)
        exerciseList.add(tricepsDipOnChair)
        val wallSit = ExerciseModel(9,"Wall sit",
            R.drawable.ic_wall_sit, isComplete = false, isSelected = false)
        exerciseList.add(wallSit)

        return exerciseList
    }
}