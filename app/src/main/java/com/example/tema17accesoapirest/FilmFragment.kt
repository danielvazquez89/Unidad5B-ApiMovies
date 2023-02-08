package com.example.tema17accesoapirest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tema17accesoapirest.databinding.FragmentFilmBinding

class FilmFragment : Fragment() {
    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvGenerosEnPelicula: RecyclerView
    val args: FilmFragmentArgs by navArgs()
    val mapGenres: HashMap<Int, String> = HashMap<Int,String>()
    val TAG = "FilmFragment"
    var data: ArrayList<String> = ArrayList()
    val genresInFilm = ArrayList<String>()
    private var adapter: CardGenreAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapGenres[37] = "Western"
        mapGenres[10752] = "Bélica"
        mapGenres[28] = "Acción"
        mapGenres[16] = "Animación"
        mapGenres[35] = "Comedia"
        mapGenres[12] = "Aventura"
        mapGenres[80] = "Crimen"
        mapGenres[99] = "Documental"
        mapGenres[18] = "Drama"
        mapGenres[14] = "Fantasía"
        mapGenres[36] = "Historia"
        mapGenres[27] = "Terror"
        mapGenres[10402] = "Música"
        mapGenres[9648] = "Misterio"
        mapGenres[10749] = "Romance"
        mapGenres[878] = "Ciencia ficción"
        mapGenres[10770] = "Película de TV"
        mapGenres[53] = "Suspense"
        for (Item: Int in args.datosPelicula.genreIds) {
            mapGenres[Item]?.let { genresInFilm.add(it) }
        }
        Glide.with(binding.miImagenPelicula.context).load(ApiRest.URL_IMAGES + args.datosPelicula.posterPath).into(binding.miImagenPelicula)
        binding.miTituloPelicula.text = args.datosPelicula.title
        binding.miDescripcionPelicula.text = args.datosPelicula.overview
        binding.miFechaPelicula.text = args.datosPelicula.releaseDate.split('-')[0]
        rvGenerosEnPelicula = binding.genresListRV
        val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvGenerosEnPelicula.layoutManager = mLayoutManager
        binding.miValoracionPelicula.text = args.datosPelicula.voteAverage.toString()
        //Creamos el adapter y lo vinculamos con el recycler
        adapter = CardGenreAdapter(genresInFilm)
        rvGenerosEnPelicula.adapter = adapter
    }
}