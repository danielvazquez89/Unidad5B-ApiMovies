package com.example.tema17accesoapirest

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tema17accesoapirest.databinding.FragmentMoviesByGenreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesByGenreFragment : Fragment() {
    private var _binding: FragmentMoviesByGenreBinding? = null
    private val binding get() = _binding!!
    var data: ArrayList<MoviesByGenreResponse.Result> = ArrayList()
    private var adapter: MoviesByGenreAdapter? = null
    val args: MoviesByGenreFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesByGenreBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val moviesByGenreRecyclerView: RecyclerView = binding.moviesByGenreRecyclerView
        moviesByGenreRecyclerView.layoutManager = GridLayoutManager(context, 2)


        adapter = MoviesByGenreAdapter(data) {
            val directions = MoviesByGenreFragmentDirections.actionMoviesByGenreFragmentToFilmFragment(it)
            findNavController().navigate(directions)
        }
        moviesByGenreRecyclerView.adapter = adapter

        ApiRest.initService()
        getMoviesByGenre(args.id)
    }

    private fun getMoviesByGenre(id: String) {
        val call = ApiRest.service.getMoviesByGenre(id)
        call.enqueue(object : Callback<MoviesByGenreResponse> {
            override fun onResponse(
                call: Call<MoviesByGenreResponse>,
                response: Response<MoviesByGenreResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    data.clear()
                    data.addAll(body.results)
                    adapter?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                }
            }

            override fun onFailure(call: Call<MoviesByGenreResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}