package com.example.tema17accesoapirest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesByGenreAdapter(private val data: ArrayList<MoviesByGenreResponse.Result>, var onClick: (MoviesByGenreResponse.Result) -> Unit) :
    RecyclerView.Adapter<MoviesByGenreAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesByGenreAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesByGenreAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
        val data = data.get(position)
        data.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            onClick(data)
        }
    }

    override fun getItemCount(): Int = data.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloPelicula = itemView.findViewById<TextView>(R.id.miTituloPelicula)
        val imagenPelicula = itemView.findViewById<ImageView>(R.id.miImagenPelicula)
        //val descripcionPelicula = itemView.findViewById<TextView>(R.id.miDescripcionPelicula)
        fun bind(item: MoviesByGenreResponse.Result) {
            tituloPelicula.text = item.title
            Glide.with(imagenPelicula.context).load(ApiRest.URL_IMAGES + item.posterPath).into(imagenPelicula)
            //descripcionPelicula.text = item.overview
            //fechaSalida.text = item.releaseDate.split('-')[0]
        }
    }
}