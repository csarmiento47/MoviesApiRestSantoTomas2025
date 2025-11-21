package com.inforcap.moviesapirest.ui

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inforcap.moviesapirest.R
import com.inforcap.moviesapirest.core.Constants
import com.inforcap.moviesapirest.databinding.ItemRvmovieBinding
import com.inforcap.moviesapirest.models.MovieEntity

class AdapterMovies(
    val context: Context,
    var movieList: List<MovieEntity>
) : RecyclerView.Adapter<AdapterMovies.MoviesViewHolder>() {

    private lateinit var binding: ItemRvmovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        binding = ItemRvmovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    inner class MoviesViewHolder(binding: ItemRvmovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(movie: MovieEntity) {
            binding.run {
                Glide.with(context)
                    .load("${Constants.API_URL_IMAGE}${movie.image}")
                    .centerCrop()
                    .apply(RequestOptions().override(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT))
                    .error(R.drawable.baseline_error_24)
                    .into(binding.ivImage)

                tvInfo.text = HtmlCompat.fromHtml(
                    "<b>Title: </b>" + movie.title +
                    "<br><b>Popularity: </b>" + movie.popularity +
                    "<br><b>Rating: </b>" + movie.rating,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )

                cvMovie.setOnClickListener {
                    showOverView(movie.title, movie.overview)
                }
            }
        }

        private fun showOverView(title: String, overview: String) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(overview)
            builder.show()
        }



    }


}