package com.sucirahmawati.androidpraktikum8.ui.user

import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ListUserAdapter(private val listUser: ArrayList<UserData>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    class ListViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: UserData) {
            with(binding){
                tvItemName.text = userData.first_name + " " + userData.last_name
                tvItemEmail.text = userData.email

                Glide.with(itemView.context)
                    .load(userData.avatar)
                    .apply(RequestOptions().override(55, 55))
                    .into(ivItemAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }


    override fun getItemCount():Int = listUser.size
}