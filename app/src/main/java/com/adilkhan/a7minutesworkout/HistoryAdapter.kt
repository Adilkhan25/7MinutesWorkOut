package com.adilkhan.a7minutesworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adilkhan.a7minutesworkout.databinding.HistoryTextViewForRecyclerViewBinding

class HistoryAdapter(private val exerciseEntity:ArrayList<ExerciseEntity>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    inner class HistoryViewHolder(binding: HistoryTextViewForRecyclerViewBinding)
        :RecyclerView.ViewHolder(binding.root){
           val tvHistory = binding.tvHistory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(HistoryTextViewForRecyclerViewBinding.inflate(LayoutInflater.from(
            parent.context
        ),parent,false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.tvHistory.text = exerciseEntity[position].date
    }

    override fun getItemCount(): Int {
        return exerciseEntity.size
    }
}