package com.example.tema17accesoapirest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class GenresAdapter(private val data: ArrayList<GenresResponse.Genre>, var onClick: (GenresResponse.Genre) -> Unit) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.celda, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        val data = data.get(position)
        data.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            onClick(data)
            //if (data)
            //  holder.mytexto.text = "\uFEFF\uD83D\uDCA5\uFEFF"
            //else
            //  holder.mytexto.text = "\uFEFF\uD83D\uDEA9\uFEFF "
        }
    }

    override fun getItemCount(): Int = data.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genero = itemView.findViewById<TextView>(R.id.genero)
        val card = itemView.findViewById<CardView>(R.id.card)
        fun bind(item: GenresResponse.Genre) {
            genero.text = item.name
            card.setCardBackgroundColor(generateColor().toInt())
            card.setOnClickListener {
                Log.v("Pulso sobre", item.id.toString())
            }
        }

        fun generateColor(): Long {
            val colors = arrayListOf(
                0xfff44336, 0xffe91e63, 0xff9c27b0, 0xff673ab7,
                0xff3f51b5, 0xff2196f3, 0xff03a9f4, 0xff00bcd4,
                0xff009688, 0xff4caf50, 0xff8bc34a, 0xffcddc39,
                0xffffeb3b, 0xffffc107, 0xffff9800, 0xffff5722,
                0xff795548, 0xff9e9e9e, 0xff607d8b, 0xff333333
            )
            return colors.random()
        }
    }
}