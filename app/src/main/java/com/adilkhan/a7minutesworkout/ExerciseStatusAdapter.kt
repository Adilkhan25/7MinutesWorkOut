package com.adilkhan.a7minutesworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adilkhan.a7minutesworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(private val mExerciseList:ArrayList<ExerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ExerciseStatusViewHolder>() {

    inner class ExerciseStatusViewHolder(binding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(binding.root){
            val tvItem = binding.tvItem
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseStatusViewHolder {
        return ExerciseStatusViewHolder(
            ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ExerciseStatusViewHolder, position: Int) {
        val exerciseModel = mExerciseList[position]
        holder.tvItem.text = exerciseModel.getId().toString()
    }

    override fun getItemCount(): Int {
        return mExerciseList.size
    }


}