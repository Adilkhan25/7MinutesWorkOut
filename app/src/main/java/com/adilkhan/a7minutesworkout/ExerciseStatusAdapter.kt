package com.adilkhan.a7minutesworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
        // this when specially design for keep track where we are
        // in the our exercise, how many exercise complete and how
        // many yet to complete
        when{
            exerciseModel.getIsSelected()->
            {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.tv_item_background_current)
                //you can also change text color
            }
            exerciseModel.getIsComplete()->
            {
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                R.drawable.tv_item_background_complete)
            }
            else->{
                holder.tvItem.background = ContextCompat.getDrawable(holder.itemView.context,
                R.drawable.tv_item_background)
            }
        }
    }

    override fun getItemCount(): Int {
        return mExerciseList.size
    }


}