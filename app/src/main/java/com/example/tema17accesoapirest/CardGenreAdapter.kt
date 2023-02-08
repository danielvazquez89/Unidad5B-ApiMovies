package com.example.tema17accesoapirest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardGenreAdapter(private val data: ArrayList<String>) :
    RecyclerView.Adapter<CardGenreAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardGenreAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_genre, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genreCardText = itemView.findViewById<TextView>(R.id.miNombreGenero)
        fun bind(item: String) {
            genreCardText.text = item
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}