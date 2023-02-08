package com.example.tema17accesoapirest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tema17accesoapirest.databinding.FragmentGenresBinding
import com.example.tema17accesoapirest.databinding.FragmentMoviesByGenreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenresFragment : Fragment() {
    private var _binding: FragmentGenresBinding? = null
    private val binding get() = _binding!!
    private var swiperefresh: SwipeRefreshLayout? = null

    // private var loader: View? = null
    private lateinit var rvGeneros: RecyclerView
    val TAG = "GenresFragment"
    var data: ArrayList<GenresResponse.Genre> = ArrayList()
    private var adapter: GenresAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGenresBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Mostrar como cuadricula
        rvGeneros = binding.rvGeneros
        //loader = findViewById<View>(R.id.loader)
        swiperefresh = binding.swiperefresh
        val mLayoutManager = GridLayoutManager(context, 2)
        rvGeneros.layoutManager = mLayoutManager

        ApiRest.initService()
        getGenres()

        //Creamos el adapter y lo vinculamos con el recycler
        adapter = GenresAdapter(data) {
            val directions = GenresFragmentDirections.actionGenresFragmentToMoviesByGenreFragment(it.id.toString())
            findNavController().navigate(directions)
        }
        rvGeneros.adapter = adapter

        swiperefresh?.setOnRefreshListener {
            getGenres()
        }
    }

    private fun getGenres() {
        val call = ApiRest.service.getGenres()
        call.enqueue(object : Callback<GenresResponse> {
            override fun onResponse(
                call: Call<GenresResponse>,
                response: Response<GenresResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    data.clear()
                    data.addAll(body.genres)
                    adapter?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                } else {
                    response.errorBody()?.string()?.let { Log.e(TAG, it) }
                }
                swiperefresh?.isRefreshing = false
            }

            override fun onFailure(call: Call<GenresResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
                swiperefresh?.isRefreshing = false
            }
        })
    }


}