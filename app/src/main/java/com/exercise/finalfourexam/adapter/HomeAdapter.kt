package com.exercise.finalfourexam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exercise.finalfourexam.databinding.ItemLayoutBinding
import com.exercise.finalfourexam.model.PersonDetails

class HomeAdapter : ListAdapter<PersonDetails, HomeAdapter.InnerViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<PersonDetails>() {
        override fun areItemsTheSame(oldItem: PersonDetails, newItem: PersonDetails): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PersonDetails, newItem: PersonDetails): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater, parent, false)
        return InnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        val personDetails = getItem(position)
        holder.bind(personDetails)
    }

    inner class InnerViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(personDetails: PersonDetails) {
            binding.apply {
                nameSurname.text = personDetails.owner
                description.text = personDetails.last_message
                time.text = personDetails.last_active
                unreadMsg.text = personDetails.unread_messages.toString()
            }
        }
    }
}