package com.inforcap.moviesapirest.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.inforcap.moviesapirest.R
import com.inforcap.moviesapirest.core.Constants
import com.inforcap.moviesapirest.databinding.ActivityMainBinding
import com.inforcap.moviesapirest.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapterMovies: AdapterMovies
    private lateinit var btnGetAll: Button
    private lateinit var btnGetPopular: Button
    private lateinit var btnGetTopRated: Button
    private lateinit var btnGetUpComing: Button
    private lateinit var tvCategory: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnGetAll = binding.btnGetAll
        btnGetPopular = binding.btnGetPopular
        btnGetTopRated = binding.btnGetTopRated
        btnGetUpComing = binding.btnGetUpcoming
        tvCategory = binding.tvCategory

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        //Inicializamos viewModel
        initRecyclerView()

        //Método por default
        tvCategory.text = Constants.CATEGORY_ALLMOVIES
        viewModel.getAllMovies()

        //Observer y eventos
        viewModel.movieList.observe(this) {
            adapterMovies.movieList = it
            adapterMovies.notifyDataSetChanged()
        }

        btnGetAll.setOnClickListener {
            tvCategory.text = Constants.CATEGORY_ALLMOVIES
            viewModel.getAllMovies()
        }

        btnGetPopular.setOnClickListener {
            tvCategory.text = Constants.CATEGORY_POPULARITY
            viewModel.getPopular()
        }

        btnGetTopRated.setOnClickListener {
            tvCategory.text = Constants.CATEGORY_TOPRATED
            viewModel.getTopRated()
        }

        btnGetUpComing.setOnClickListener {
            tvCategory.text = Constants.CATEGORY_UPCOMING
            viewModel.getUpComing()
        }



    }

    private fun initRecyclerView(){
        val layoutManager = GridLayoutManager(this, 3)
        binding.rvMovies.layoutManager = layoutManager
        adapterMovies = AdapterMovies(this, arrayListOf())
        binding.rvMovies.adapter = adapterMovies
    }
}